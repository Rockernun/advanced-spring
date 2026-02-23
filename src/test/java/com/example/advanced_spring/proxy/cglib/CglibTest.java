package com.example.advanced_spring.proxy.cglib;

import com.example.advanced_spring.proxy.cglib.code.TimeMethodInterceptor;
import com.example.advanced_spring.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();

        /**
         * 15:10:25.409 [Test worker] INFO com.example.advanced_spring.proxy.cglib.CglibTest -- targetClass=class com.example.advanced_spring.proxy.common.service.ConcreteService
         * 15:10:25.411 [Test worker] INFO com.example.advanced_spring.proxy.cglib.CglibTest -- proxyClass=class com.example.advanced_spring.proxy.common.service.ConcreteService$$EnhancerByCGLIB$$9dae7170
         * 15:10:25.411 [Test worker] INFO com.example.advanced_spring.proxy.cglib.code.TimeMethodInterceptor -- TimeProxy 실행
         * 15:10:25.417 [Test worker] INFO com.example.advanced_spring.proxy.common.service.ConcreteService -- ConcreteService 호출...
         * 15:10:25.417 [Test worker] INFO com.example.advanced_spring.proxy.cglib.code.TimeMethodInterceptor -- TimeProxy 종료 resultTime=6
         */
    }
}
