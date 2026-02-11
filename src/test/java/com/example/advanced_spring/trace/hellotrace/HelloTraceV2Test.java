package com.example.advanced_spring.trace.hellotrace;

import com.example.advanced_spring.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 helloTrace = new HelloTraceV2();
        TraceStatus status1 = helloTrace.begin("hello1");
        TraceStatus status2 = helloTrace.beginSync(status1.getTraceId(), "hello2");
        helloTrace.end(status2);
        helloTrace.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 helloTrace = new HelloTraceV2();
        TraceStatus status1 = helloTrace.begin("hello1");
        TraceStatus status2 = helloTrace.beginSync(status1.getTraceId(), "hello2");
        helloTrace.exception(status1, new IllegalStateException());
        helloTrace.exception(status2, new IllegalStateException());
    }
}