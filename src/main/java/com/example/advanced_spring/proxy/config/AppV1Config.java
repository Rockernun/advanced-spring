package com.example.advanced_spring.proxy.config;

import com.example.advanced_spring.proxy.v1.ProxyOrderControllerV1;
import com.example.advanced_spring.proxy.v1.ProxyOrderControllerV1Impl;
import com.example.advanced_spring.proxy.v1.ProxyOrderRepositoryV1;
import com.example.advanced_spring.proxy.v1.ProxyOrderRepositoryV1Impl;
import com.example.advanced_spring.proxy.v1.ProxyOrderServiceV1;
import com.example.advanced_spring.proxy.v1.ProxyOrderServiceV1Impl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV1Config {

    @Bean
    public ProxyOrderControllerV1 orderControllerV1() {
        return new ProxyOrderControllerV1Impl(orderServiceV1());
    }

    @Bean
    public ProxyOrderServiceV1 orderServiceV1() {
        return new ProxyOrderServiceV1Impl(orderRepositoryV1());
    }

    @Bean
    public ProxyOrderRepositoryV1 orderRepositoryV1() {
        return new ProxyOrderRepositoryV1Impl();
    }
}
