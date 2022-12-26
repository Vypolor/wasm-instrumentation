package com.ssau.instrumentation.parser.writers.section.info;

import com.ssau.instrumentation.model.enums.SectionType;

public class SectionInfo {
    private final SectionType sectionType;
    private final int sectionLength;

    public SectionInfo(SectionType sectionType, int sectionLength) {
        this.sectionType = sectionType;
        this.sectionLength = sectionLength;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public int getSectionLength() {
        return sectionLength;
    }
}
