package com.example.advanced_spring.proxy.pureproxy.proxy;

import com.example.advanced_spring.proxy.pureproxy.proxy.code.ProxyPatternClient;
import com.example.advanced_spring.proxy.pureproxy.proxy.code.RealSubject;
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
}
