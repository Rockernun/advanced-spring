package com.example.advanced_spring.trace.logtrace;

import com.example.advanced_spring.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
