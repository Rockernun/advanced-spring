package com.example.advanced_spring.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {

    private String nameStore;

    public String logic(String name) {
        log.info("저장 시도 name={} -> 현재 nameStore={}", name, nameStore);
        nameStore = name;
        log.info("name={} 저장 성공 -> 현재 nameStore={}", name, nameStore);
        sleep(1000);
        log.info("nameStore로부터 조회 nameStore={}", nameStore);
        return nameStore;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
