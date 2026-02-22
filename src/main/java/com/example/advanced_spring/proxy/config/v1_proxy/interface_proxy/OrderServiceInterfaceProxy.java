package com.example.advanced_spring.proxy.config.v1_proxy.interface_proxy;

import com.example.advanced_spring.proxy.v1.ProxyOrderServiceV1;
import com.example.advanced_spring.trace.TraceStatus;
import com.example.advanced_spring.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements ProxyOrderServiceV1 {

    private final ProxyOrderServiceV1 target;
    private final LogTrace logTrace;

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
