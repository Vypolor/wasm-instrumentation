package com.ssau.instrumentation.parser.writers.impl;

import com.ssau.instrumentation.parser.writers.SectionWriter;

import java.util.List;

public class FunctionSectionWriter implements SectionWriter {

    private final static byte SECTION_IDX = 0x03;

    private final int sectionLength;
    private final List<Integer> functionTypes;

    public FunctionSectionWriter(int sectionLength, List<Integer> functionTypes) {
        this.sectionLength = sectionLength;
        this.functionTypes = functionTypes;
    }

    @Override
    public void write(List<Byte> bytes) {
        bytes.add(SECTION_IDX);
        bytes.add((byte) sectionLength);
        bytes.add((byte) functionTypes.size());
        for (Integer functionType : functionTypes) {
            int funcType = functionType;
            bytes.add((byte) funcType);
        }
    }
}
