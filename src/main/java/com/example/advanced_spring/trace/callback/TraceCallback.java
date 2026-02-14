package com.example.advanced_spring.trace.callback;

public interface TraceCallback<T> {
    T call();
}
