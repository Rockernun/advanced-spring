package com.example.advanced_spring.proxy.config.v1_proxy;

import com.example.advanced_spring.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import com.example.advanced_spring.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import com.example.advanced_spring.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import com.example.advanced_spring.proxy.v1.ProxyOrderControllerV1;
import com.example.advanced_spring.proxy.v1.ProxyOrderControllerV1Impl;
import com.example.advanced_spring.proxy.v1.ProxyOrderRepositoryV1;
import com.example.advanced_spring.proxy.v1.ProxyOrderRepositoryV1Impl;
import com.example.advanced_spring.proxy.v1.ProxyOrderServiceV1;
import com.example.advanced_spring.proxy.v1.ProxyOrderServiceV1Impl;
import com.example.advanced_spring.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public ProxyOrderControllerV1 proxyOrderController(LogTrace logTrace) {
        ProxyOrderControllerV1Impl controllerImpl = new ProxyOrderControllerV1Impl(proxyOrderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }

    @Bean
    public ProxyOrderServiceV1 proxyOrderService(LogTrace logTrace) {
        ProxyOrderServiceV1Impl serviceImpl = new ProxyOrderServiceV1Impl(proxyOrderRepository(logTrace));
        return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
    }

    @Bean
    public ProxyOrderRepositoryV1 proxyOrderRepository(LogTrace logTrace) {
        ProxyOrderRepositoryV1Impl repositoryImpl = new ProxyOrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, logTrace);
    }
}
