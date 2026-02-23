package com.example.advanced_spring.proxy.config.v1_proxy.concrete_proxy;

import com.example.advanced_spring.proxy.v2.ProxyOrderControllerV2;
import com.example.advanced_spring.trace.TraceStatus;
import com.example.advanced_spring.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends ProxyOrderControllerV2 {

    private ProxyOrderControllerV2 target;
    private final LogTrace logTrace;

    public OrderControllerConcreteProxy(ProxyOrderControllerV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderController.request()");
            String result = target.request(itemId);
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
