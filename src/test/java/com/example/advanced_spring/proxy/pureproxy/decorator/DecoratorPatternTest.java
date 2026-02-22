package com.example.advanced_spring.proxy.pureproxy.decorator;

import com.example.advanced_spring.proxy.pureproxy.decorator.code.Component;
import com.example.advanced_spring.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import com.example.advanced_spring.proxy.pureproxy.decorator.code.RealComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();

        /**
         * 22:06:32.422 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.decorator.code.RealComponent -- RealComponent 실행...
         * 22:06:32.424 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.decorator.code.DecoratorPatternClient -- result=data
         */
    }
}
