package com.ssau.instrumentation.model.section.impl;

import com.ssau.instrumentation.model.section.ImportSectionEntryType;

public class Module extends ImportSectionEntryType {
    final int index;

    public Module(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Module{" +
                "index=" + index +
                '}';
    }
}
