package com.ssau.instrumentation.model.segments;

import com.ssau.instrumentation.model.Limits;

public class MemorySegment {
    private final Limits limits;

    public MemorySegment(Limits limits) {
        this.limits = limits;
    }

    @Override
    public String toString() {
        return "{" +
                "limits=" + limits +
                '}';
    }

    public Limits limits() {
        return this.limits;
    }
}
