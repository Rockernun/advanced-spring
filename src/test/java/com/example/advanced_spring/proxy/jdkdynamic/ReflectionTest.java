package com.example.advanced_spring.proxy.jdkdynamic;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

    @Test
    void reflectionV0() {
        Hello target = new Hello();

        log.info("Start...");
        String result1 = target.methodA();
        log.info("result1: {}", result1);

        log.info("Start...");
        String result2 = target.methodB();
        log.info("result2: {}", result2);

        /**
         * 13:03:01.047 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest -- Start...
         * 13:03:01.049 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest$Hello -- calling A...
         * 13:03:01.049 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest -- result1: A
         * 13:03:01.049 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest -- Start...
         * 13:03:01.049 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest$Hello -- calling B...
         * 13:03:01.049 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest -- result2: B
         */
    }

    @Test
    void reflectionV1() throws Exception {
        // 클래스 정보
        Class classHello = Class.forName("com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method methodA = classHello.getMethod("methodA");
        Object result1 = methodA.invoke(target);
        log.info("result1: {}", result1);

        Method methodB = classHello.getMethod("methodB");
        Object result2 = methodB.invoke(target);
        log.info("result2: {}", result2);

        /**
         * 13:07:35.329 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest$Hello -- calling A...
         * 13:07:35.331 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest -- result1: A
         * 13:07:35.331 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest$Hello -- calling B...
         * 13:07:35.331 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest -- result2: B
         */
    }

    @Test
    void reflectionV2() throws Exception {
        Class classHello = Class.forName("com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodA = classHello.getMethod("methodA");
        dynamicCall(methodA, target);

        Method methodB = classHello.getMethod("methodB");
        dynamicCall(methodB, target);

        /**
         * 13:13:49.726 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest -- Start...
         * 13:13:49.728 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest$Hello -- calling A...
         * 13:13:49.728 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest -- result: A
         * 13:13:49.729 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest -- Start...
         * 13:13:49.729 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest$Hello -- calling B...
         * 13:13:49.729 [Test worker] INFO com.example.advanced_spring.proxy.jdkdynamic.ReflectionTest -- result: B
         */
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("Start...");
        Object result = method.invoke(target);
        log.info("result: {}", result);
    }

    @Slf4j
    static class Hello {
        public String methodA() {
            log.info("calling A...");
            return "A";
        }

        public String methodB() {
            log.info("calling B...");
            return "B";
        }
    }
}
