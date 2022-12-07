package com.ssau.instrumentation.model;

import com.ssau.instrumentation.model.enums.Type;

public class Table {

    private final Type type;
    private final Limits limits;

    public Table(Type type, Limits limits) {
        this.type = type;
        this.limits = limits;
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", limits=" + limits +
                '}';
    }
}
