package com.example.advanced_spring.proxy.config.v1_proxy.concrete_proxy;

import com.example.advanced_spring.proxy.v2.ProxyOrderRepositoryV2;
import com.example.advanced_spring.proxy.v2.ProxyOrderServiceV2;
import com.example.advanced_spring.trace.TraceStatus;
import com.example.advanced_spring.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends ProxyOrderServiceV2 {

    private final ProxyOrderServiceV2 target;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy(ProxyOrderServiceV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.orderItem()");
            target.orderItem(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
