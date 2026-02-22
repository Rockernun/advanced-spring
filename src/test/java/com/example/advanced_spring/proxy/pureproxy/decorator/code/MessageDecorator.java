package com.example.advanced_spring.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 실행...");

        String rawResult = component.operation();
        String decoResult = "*****" + rawResult + "*****";
        log.info("MessageDecorator 꾸미기 적용 전={}, 적용 후={}", rawResult, decoResult);

        return decoResult;
    }
}
