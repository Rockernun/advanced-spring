package com.example.advanced_spring.proxy.config.v1_proxy.interface_proxy;

import com.example.advanced_spring.proxy.v1.ProxyOrderRepositoryV1;
import com.example.advanced_spring.trace.TraceStatus;
import com.example.advanced_spring.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements ProxyOrderRepositoryV1 {

    private final ProxyOrderRepositoryV1 target;
    private final LogTrace logTrace;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.save()");
            target.save(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
