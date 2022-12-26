package com.ssau.instrumentation.model.section.impl;

import com.ssau.instrumentation.model.Limits;
import com.ssau.instrumentation.model.enums.Type;
import com.ssau.instrumentation.model.section.ImportSectionEntryType;

public class Table extends ImportSectionEntryType {
    final Type type;
    final Limits limits;

    public Table(Type type, Limits limits) {
        this.type = type;
        this.limits = limits;
    }

    @Override
    public String toString() {
        return "Table{" +
                "type=" + type +
                ", limits=" + limits +
                '}';
    }

    public Type getType() {
        return type;
    }

    public Limits getLimits() {
        return limits;
    }
}
