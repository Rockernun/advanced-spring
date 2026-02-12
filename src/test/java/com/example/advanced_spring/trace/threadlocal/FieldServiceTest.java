package com.example.advanced_spring.trace.threadlocal;

import com.example.advanced_spring.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("Thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("Thread-B");

        threadA.start();
//        sleep(2000);
        sleep(100);
        threadB.start();

        sleep(3000);
        log.info("main end");

        /**
         * 14:55:49.375 [Test worker] INFO com.example.advanced_spring.trace.threadlocal.FieldServiceTest -- main start
         * 14:55:49.377 [Thread-A] INFO com.example.advanced_spring.trace.threadlocal.code.FieldService -- 저장 시도 name=userA -> 현재 nameStore=null
         * 14:55:49.378 [Thread-A] INFO com.example.advanced_spring.trace.threadlocal.code.FieldService -- name=userA 저장 성공 -> 현재 nameStore=userA
         * 14:55:49.478 [Thread-B] INFO com.example.advanced_spring.trace.threadlocal.code.FieldService -- 저장 시도 name=userB -> 현재 nameStore=userA
         * 14:55:49.479 [Thread-B] INFO com.example.advanced_spring.trace.threadlocal.code.FieldService -- name=userB 저장 성공 -> 현재 nameStore=userB
         * 14:55:50.379 [Thread-A] INFO com.example.advanced_spring.trace.threadlocal.code.FieldService -- nameStore로부터 조회 nameStore=userB
         * 14:55:50.480 [Thread-B] INFO com.example.advanced_spring.trace.threadlocal.code.FieldService -- nameStore로부터 조회 nameStore=userB
         * 14:55:52.480 [Test worker] INFO com.example.advanced_spring.trace.threadlocal.FieldServiceTest -- main end
         */
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
