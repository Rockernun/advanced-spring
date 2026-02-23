package com.example.advanced_spring.proxy.pureproxy.concreteproxy;

import com.example.advanced_spring.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import com.example.advanced_spring.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();

        // 11:42:55.390 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.concreteproxy.code.ConcreteLogic -- ConcreteLogic 실행...
    }
}
