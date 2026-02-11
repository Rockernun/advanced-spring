package com.example.advanced_spring.trace.hellotrace;

import com.example.advanced_spring.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {

    @Test
    void begin_end() {
        HelloTraceV1 helloTrace = new HelloTraceV1();
        TraceStatus status = helloTrace.begin("hello");
        helloTrace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTraceV1 helloTrace = new HelloTraceV1();
        TraceStatus status = helloTrace.begin("hello");
        helloTrace.exception(status, new IllegalStateException());
    }
}