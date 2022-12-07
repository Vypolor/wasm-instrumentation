package com.ssau.instrumentation.model.section.impl;

import com.ssau.instrumentation.model.section.ImportSectionEntryType;

public class Function extends ImportSectionEntryType {
    final int index;

    public Function(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Function{" +
                "index=" + index +
                '}';
    }
}
