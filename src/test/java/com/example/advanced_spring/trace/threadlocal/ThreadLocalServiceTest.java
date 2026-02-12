package com.example.advanced_spring.trace.threadlocal;

import com.example.advanced_spring.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");

        Runnable userA = () -> {
            service.logic("userA");
        };

        Runnable userB = () -> {
            service.logic("userB");
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
         * 15:13:43.632 [Test worker] INFO com.example.advanced_spring.trace.threadlocal.ThreadLocalServiceTest -- main start
         * 15:13:43.634 [Thread-A] INFO com.example.advanced_spring.trace.threadlocal.code.ThreadLocalService -- 저장 시도 name=userA -> 현재 nameStore=null
         * 15:13:43.635 [Thread-A] INFO com.example.advanced_spring.trace.threadlocal.code.ThreadLocalService -- name=userA 저장 성공 -> 현재 nameStore=userA
         * 15:13:43.735 [Thread-B] INFO com.example.advanced_spring.trace.threadlocal.code.ThreadLocalService -- 저장 시도 name=userB -> 현재 nameStore=null
         * 15:13:43.735 [Thread-B] INFO com.example.advanced_spring.trace.threadlocal.code.ThreadLocalService -- name=userB 저장 성공 -> 현재 nameStore=userB
         * 15:13:44.637 [Thread-A] INFO com.example.advanced_spring.trace.threadlocal.code.ThreadLocalService -- nameStore로부터 조회 nameStore=userA
         * 15:13:44.736 [Thread-B] INFO com.example.advanced_spring.trace.threadlocal.code.ThreadLocalService -- nameStore로부터 조회 nameStore=userB
         * 15:13:46.736 [Test worker] INFO com.example.advanced_spring.trace.threadlocal.ThreadLocalServiceTest -- main end
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
