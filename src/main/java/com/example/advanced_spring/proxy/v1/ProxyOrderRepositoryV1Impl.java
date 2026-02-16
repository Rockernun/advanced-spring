package com.example.advanced_spring.proxy.v1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyOrderRepositoryV1Impl implements ProxyOrderRepositoryV1 {

    @Override
    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
