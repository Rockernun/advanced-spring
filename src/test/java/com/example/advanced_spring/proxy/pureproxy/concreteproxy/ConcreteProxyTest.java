package com.example.advanced_spring.proxy.pureproxy.concreteproxy;

import com.example.advanced_spring.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import com.example.advanced_spring.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import com.example.advanced_spring.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();

        // 11:42:55.390 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.concreteproxy.code.ConcreteLogic -- ConcreteLogic 실행...
    }

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.execute();

        /**
         * 11:53:34.801 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.concreteproxy.code.TimeProxy -- TimeDecorator 실행...
         * 11:53:34.803 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.concreteproxy.code.ConcreteLogic -- ConcreteLogic 실행...
         * 11:53:34.803 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.concreteproxy.code.TimeProxy -- TimeDecorator 종료 resultTime=0
         */
    }
}
