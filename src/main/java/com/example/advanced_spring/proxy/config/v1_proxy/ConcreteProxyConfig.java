package com.example.advanced_spring.proxy.config.v1_proxy;

import com.example.advanced_spring.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import com.example.advanced_spring.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import com.example.advanced_spring.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import com.example.advanced_spring.proxy.v2.ProxyOrderControllerV2;
import com.example.advanced_spring.proxy.v2.ProxyOrderRepositoryV2;
import com.example.advanced_spring.proxy.v2.ProxyOrderServiceV2;
import com.example.advanced_spring.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public ProxyOrderControllerV2 orderControllerV2(LogTrace logTrace) {
        ProxyOrderControllerV2 controllerImpl = new ProxyOrderControllerV2(orderServiceV2(logTrace));
        return new OrderControllerConcreteProxy(controllerImpl, logTrace);
    }

    @Bean
    public ProxyOrderServiceV2 orderServiceV2(LogTrace logTrace) {
        ProxyOrderServiceV2 serviceImpl = new ProxyOrderServiceV2(orderRepositoryV2(logTrace));
        return new OrderServiceConcreteProxy(serviceImpl, logTrace);
    }

    @Bean
    public ProxyOrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        ProxyOrderRepositoryV2 repositoryImpl = new ProxyOrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(repositoryImpl, logTrace);
    }
}
