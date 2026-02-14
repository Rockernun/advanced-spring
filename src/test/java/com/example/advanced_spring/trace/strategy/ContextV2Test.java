package com.example.advanced_spring.trace.strategy;

import com.example.advanced_spring.trace.strategy.code.ContextV2;
import com.example.advanced_spring.trace.strategy.code.strategy.Strategy;
import com.example.advanced_spring.trace.strategy.code.strategy.StrategyLogic1;
import com.example.advanced_spring.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());

        /**
         * 20:00:52.108 [Test worker] INFO com.example.advanced_spring.trace.strategy.code.strategy.StrategyLogic1 -- 비즈니스 로직1 실행...
         * 20:00:52.110 [Test worker] INFO com.example.advanced_spring.trace.strategy.code.ContextV2 -- resultTime: 2
         * 20:00:52.110 [Test worker] INFO com.example.advanced_spring.trace.strategy.code.strategy.StrategyLogic2 -- 비즈니스 로직2 실행...
         * 20:00:52.111 [Test worker] INFO com.example.advanced_spring.trace.strategy.code.ContextV2 -- resultTime: 1
         */
    }

    /**
     * 익명 클래스 사용
     */
    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행...");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행...");
            }
        });
    }

    /**
     * 람다 활용
     */
    @Test
    void strategyV3() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직1 실행..."));
        context.execute(() -> log.info("비즈니스 로직2 실행..."));
    }
}
