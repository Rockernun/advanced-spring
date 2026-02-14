package com.example.advanced_spring.trace.strategy;

import com.example.advanced_spring.trace.strategy.code.ContextV1;
import com.example.advanced_spring.trace.strategy.code.strategy.Strategy;
import com.example.advanced_spring.trace.strategy.code.strategy.StrategyLogic1;
import com.example.advanced_spring.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직1 실행...");
        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직2 실행...");
        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    /**
     * 전략 패턴 적용
     */
    @Test
    void strategyV1() {
        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();
        /**
         * 19:33:29.600 [Test worker] INFO com.example.advanced_spring.trace.strategy.code.strategy.StrategyLogic1 -- 비즈니스 로직1 실행...
         * 19:33:29.601 [Test worker] INFO com.example.advanced_spring.trace.strategy.code.ContextV1 -- resultTime: 2
         */

        Strategy strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
        /**
         * 19:33:29.602 [Test worker] INFO com.example.advanced_spring.trace.strategy.code.strategy.StrategyLogic2 -- 비즈니스 로직2 실행...
         * 19:33:29.602 [Test worker] INFO com.example.advanced_spring.trace.strategy.code.ContextV1 -- resultTime: 0
         */
    }

    /**
     * 익명 내부 클래스 사용
     */
    @Test
    void strategyV2() {
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행...");
            }
        };
        log.info("strategyLogic1 = {}", strategyLogic1.getClass());
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        /**
         * 19:44:17.506 [Test worker] INFO com.example.advanced_spring.trace.strategy.ContextV1Test -- strategyLogic1 = class com.example.advanced_spring.trace.strategy.ContextV1Test$1
         * 19:44:17.507 [Test worker] INFO com.example.advanced_spring.trace.strategy.ContextV1Test -- 비즈니스 로직1 실행...
         * 19:44:17.508 [Test worker] INFO com.example.advanced_spring.trace.strategy.code.ContextV1 -- resultTime: 1
         */

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행...");
            }
        };
        log.info("strategyLogic2 = {}", strategyLogic2.getClass());
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();

        /**
         * 19:44:17.508 [Test worker] INFO com.example.advanced_spring.trace.strategy.ContextV1Test -- strategyLogic2 = class com.example.advanced_spring.trace.strategy.ContextV1Test$2
         * 19:44:17.508 [Test worker] INFO com.example.advanced_spring.trace.strategy.ContextV1Test -- 비즈니스 로직2 실행...
         * 19:44:17.508 [Test worker] INFO com.example.advanced_spring.trace.strategy.code.ContextV1 -- resultTime: 0
         */
    }

    /**
     * 익명 내부 클래스를 변수에 담아두지 말고, 생성하면서 바로 ContextV1에 전달
     */
    @Test
    void strategyV3() {
        ContextV1 contextV1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행...");
            }
        });
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행...");
            }
        });
        contextV2.execute();
    }

    /**
     * 람다 사용도 가능
     */
    @Test
    void strategyV4() {
        ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직1 실행..."));
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직2 실행..."));
        contextV2.execute();
    }
}
