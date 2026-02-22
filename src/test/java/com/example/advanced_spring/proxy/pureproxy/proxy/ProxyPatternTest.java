package com.example.advanced_spring.proxy.pureproxy.proxy;

import com.example.advanced_spring.proxy.pureproxy.proxy.code.CacheProxy;
import com.example.advanced_spring.proxy.pureproxy.proxy.code.ProxyPatternClient;
import com.example.advanced_spring.proxy.pureproxy.proxy.code.RealSubject;
import com.example.advanced_spring.proxy.pureproxy.proxy.code.Subject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();

        /**
         * 21:24:00.298 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.proxy.code.RealSubject -- 실제 객체 호출...
         * 21:24:01.301 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.proxy.code.RealSubject -- 실제 객체 호출...
         * 21:24:02.302 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.proxy.code.RealSubject -- 실제 객체 호출...
         */
    }

    @Test
    void cacheProxyTest() {
        Subject subject = new RealSubject();
        Subject clientSubject = new CacheProxy(subject);
        ProxyPatternClient client = new ProxyPatternClient(clientSubject);
        client.execute();
        client.execute();
        client.execute();

        /**
         * 21:39:55.619 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.proxy.code.CacheProxy -- 프록시 호출...
         * 21:39:55.621 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.proxy.code.RealSubject -- 실제 객체 호출...
         *
         * (기존 호출을 통해 캐시된 값이 존재하므로 값을 즉시 반환)
         * 21:39:56.623 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.proxy.code.CacheProxy -- 프록시 호출...
         * 21:39:56.624 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.proxy.code.CacheProxy -- 프록시 호출...
         */
    }
}
