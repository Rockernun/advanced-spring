package com.example.advanced_spring.trace.hellotrace;

import com.example.advanced_spring.trace.TraceId;
import com.example.advanced_spring.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV2 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(),
                addSpace(START_PREFIX, traceId.getDepth()), message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    public TraceStatus beginSync(TraceId beforeTraceId, String message) {
        TraceId nextId = beforeTraceId.createNextId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", nextId.getId(),
                addSpace(START_PREFIX, nextId.getDepth()), message);

        return new TraceStatus(nextId, startTimeMs, message);
    }

    public void end(TraceStatus status) {
        complete(status, null);
    }

    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getDepth()), status.getMessage(),
                    resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getDepth()), status.getMessage(), resultTimeMs,
                    e.toString());
        }
    }

    private String addSpace(String prefix, int depth) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < depth; i++) {
            sb.append( (i == depth - 1) ? "|" + prefix : "| ");
        }

        return sb.toString();
    }
}
