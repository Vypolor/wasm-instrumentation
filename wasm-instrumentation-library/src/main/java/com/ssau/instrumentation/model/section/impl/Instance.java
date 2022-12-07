package com.ssau.instrumentation.model.section.impl;

import com.ssau.instrumentation.model.section.ImportSectionEntryType;

public class Instance extends ImportSectionEntryType {

    final int index;

    public Instance(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Instance{" +
                "index=" + index +
                '}';
    }
}
