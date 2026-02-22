package com.example.advanced_spring.proxy.pureproxy.decorator;

import com.example.advanced_spring.proxy.pureproxy.decorator.code.Component;
import com.example.advanced_spring.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import com.example.advanced_spring.proxy.pureproxy.decorator.code.MessageDecorator;
import com.example.advanced_spring.proxy.pureproxy.decorator.code.RealComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
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

    @Test
    void decoratorCase1() {
        Component realComponent = new RealComponent();
        MessageDecorator decorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(decorator);

        client.execute();

        /**
         * 22:19:41.223 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.decorator.code.MessageDecorator -- MessageDecorator 실행...
         * 22:19:41.225 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.decorator.code.RealComponent -- RealComponent 실행...
         * 22:19:41.225 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.decorator.code.MessageDecorator -- MessageDecorator 꾸미기 적용 전=data, 적용 후=*****data*****
         * 22:19:41.226 [Test worker] INFO com.example.advanced_spring.proxy.pureproxy.decorator.code.DecoratorPatternClient -- result=*****data*****
         */
    }
}
