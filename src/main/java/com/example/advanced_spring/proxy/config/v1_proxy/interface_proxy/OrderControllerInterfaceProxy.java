package com.example.advanced_spring.proxy.config.v1_proxy.interface_proxy;

import com.example.advanced_spring.proxy.v1.ProxyOrderControllerV1;
import com.example.advanced_spring.trace.TraceStatus;
import com.example.advanced_spring.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements ProxyOrderControllerV1 {

    private final ProxyOrderControllerV1 target;
    private final LogTrace logTrace;

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

    @Override
    public String noLog() {
        return target.noLog();
    }
}
