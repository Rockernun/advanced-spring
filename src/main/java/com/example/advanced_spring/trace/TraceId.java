package com.example.advanced_spring.trace;

import java.util.UUID;
import lombok.Getter;

@Getter
public class TraceId {

    private final String id;
    private final int depth;

    public TraceId() {
        this.id = createId();
        this.depth = 0;
    }

    private TraceId(String id, int depth) {
        this.id = id;
        this.depth = depth;
    }

    public boolean isFirstDepth() {
        return depth == 0;
    }

    public TraceId createPreviousId() {
        return new TraceId(id, depth - 1);
    }

    public TraceId createNextId() {
        return new TraceId(id, depth + 1);
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
