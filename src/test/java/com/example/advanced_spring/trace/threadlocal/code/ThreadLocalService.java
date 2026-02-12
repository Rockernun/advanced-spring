package com.example.advanced_spring.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("저장 시도 name={} -> 현재 nameStore={}", name, nameStore.get());
        nameStore.set(name);
        log.info("name={} 저장 성공 -> 현재 nameStore={}", name, nameStore.get());
        sleep(1000);
        log.info("nameStore로부터 조회 nameStore={}", nameStore.get());
        return nameStore.get();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
