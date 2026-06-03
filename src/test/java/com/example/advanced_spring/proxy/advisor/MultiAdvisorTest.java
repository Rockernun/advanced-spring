package com.example.advanced_spring.proxy.advisor;

import com.example.advanced_spring.proxy.common.service.ServiceImpl;
import com.example.advanced_spring.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class MultiAdvisorTest {

    @Test
    void multiAdvisorTest1() {
        // 프록시1 생성
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory1 = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        proxyFactory1.addAdvisor(advisor1);
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        // 프록시2 생성
        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        proxyFactory1.addAdvisor(advisor2);
        ServiceInterface proxy2 = (ServiceInterface) proxyFactory1.getProxy();

        // 실행
        proxy2.save();

        /**
         * 23:33:12.266 [Test worker] INFO com.example.advanced_spring.proxy.advisor.MultiAdvisorTest$Advice1 -- advice1 호출...
         * 23:33:12.267 [Test worker] INFO com.example.advanced_spring.proxy.advisor.MultiAdvisorTest$Advice2 -- advice2 호출...
         * 23:33:12.268 [Test worker] INFO com.example.advanced_spring.proxy.common.service.ServiceImpl -- save 호출...
         */
    }

    @Test
    void multiAdvisorTest2() {
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());

        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory1 = new ProxyFactory(target);

        proxyFactory1.addAdvisor(advisor1);
        proxyFactory1.addAdvisor(advisor2);
        ServiceInterface proxy = (ServiceInterface) proxyFactory1.getProxy();

        // 실행
        proxy.save();

        /**
         * 23:40:21.378 [Test worker] INFO com.example.advanced_spring.proxy.advisor.MultiAdvisorTest$Advice1 -- advice1 호출...
         * 23:40:21.380 [Test worker] INFO com.example.advanced_spring.proxy.advisor.MultiAdvisorTest$Advice2 -- advice2 호출...
         * 23:40:21.380 [Test worker] INFO com.example.advanced_spring.proxy.common.service.ServiceImpl -- save 호출...
         */
    }

    @Slf4j
    static class Advice1 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice1 호출...");
            return invocation.proceed();
        }
    }

    @Slf4j
    static class Advice2 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice2 호출...");
            return invocation.proceed();
        }
    }

}
