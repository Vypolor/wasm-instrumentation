package com.ssau.instrumentation.model;

import com.ssau.instrumentation.model.enums.Type;

public class Local {
    private final int count;
    private final Type type;

    public Local(int count, Type type) {
        this.count = count;
        this.type = type;
    }

    @Override
    public String toString() {
        return count + ":" + type;
    }
}
