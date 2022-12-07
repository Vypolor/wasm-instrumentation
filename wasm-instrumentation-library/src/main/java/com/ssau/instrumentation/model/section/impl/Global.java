package com.ssau.instrumentation.model.section.impl;

import com.ssau.instrumentation.model.enums.Type;
import com.ssau.instrumentation.model.section.ImportSectionEntryType;

public class Global extends ImportSectionEntryType {
    final Type type;
    final boolean mutable;

    public Global(Type type, boolean mutable) {
        this.type = type;
        this.mutable = mutable;
    }

    @Override
    public String toString() {
        return "Global{" +
                "type=" + type +
                ", mutable=" + mutable +
                '}';
    }
}
