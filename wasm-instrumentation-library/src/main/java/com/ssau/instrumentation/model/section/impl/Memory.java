package com.ssau.instrumentation.model.section.impl;

import com.ssau.instrumentation.model.Limits;
import com.ssau.instrumentation.model.section.ImportSectionEntryType;

public class Memory extends ImportSectionEntryType {
    final Limits limits;

    public Memory(Limits limits) {
        this.limits = limits;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "limits=" + limits +
                '}';
    }
}
