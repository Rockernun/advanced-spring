package com.example.advanced_spring;

import com.example.advanced_spring.trace.logtrace.FieldLogTrace;
import com.example.advanced_spring.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
