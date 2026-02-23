package com.example.advanced_spring.proxy.jdkdynamic;

import com.example.advanced_spring.proxy.jdkdynamic.code.AInterface;
import com.example.advanced_spring.proxy.jdkdynamic.code.AInterfaceImpl;
import com.example.advanced_spring.proxy.jdkdynamic.code.BInterface;
import com.example.advanced_spring.proxy.jdkdynamic.code.BInterfaceImpl;
import com.example.advanced_spring.proxy.jdkdynamic.code.TimeInvocationHandler;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AInterfaceImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        AInterface proxy = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class}, handler);

        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        /**
         * 13:39:51.584 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.code.TimeInvocationHandler -- TimeProxy 실행...
         * 13:39:51.586 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.code.AInterfaceImpl -- A 호출...
         * 13:39:51.586 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.code.TimeInvocationHandler -- TimeProxy 종료 resultTime=0
         * 13:39:51.587 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.JdkDynamicProxyTest -- targetClass=class com.example.advanced_spring.proxy.jdkdynamic.code.AInterfaceImpl
         * 13:39:51.587 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.JdkDynamicProxyTest -- proxyClass=class jdk.proxy3.$Proxy12
         */
    }

    @Test
    void dynamicB() {
        BInterface target = new BInterfaceImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        BInterface proxy = (BInterface) Proxy.newProxyInstance(
                BInterface.class.getClassLoader(),
                new Class[]{BInterface.class}, handler);

        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        /**
         * 13:40:09.633 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.code.TimeInvocationHandler -- TimeProxy 실행...
         * 13:40:09.635 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.code.BInterfaceImpl -- B 호출...
         * 13:40:09.635 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.code.TimeInvocationHandler -- TimeProxy 종료 resultTime=0
         * 13:40:09.636 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.JdkDynamicProxyTest -- targetClass=class com.example.advanced_spring.proxy.jdkdynamic.code.BInterfaceImpl
         * 13:40:09.636 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.JdkDynamicProxyTest -- proxyClass=class jdk.proxy3.$Proxy12
         */
    }
}
