package com.ssau.instrumentation.parser.writers.impl;

import com.ssau.instrumentation.model.Global;
import com.ssau.instrumentation.parser.writers.SectionWriter;

import java.util.List;

public class GlobalSectionWriter implements SectionWriter {

    private final static byte SECTION_IDX = 0x06;

    private final int sectionLength;
    private final List<Global> globals;

    public GlobalSectionWriter(int sectionLength, List<Global> globals) {
        this.sectionLength = sectionLength;
        this.globals = globals;
    }

    @Override
    public void write(List<Byte> bytes) {
        bytes.add(SECTION_IDX);
        bytes.add((byte) sectionLength);
        bytes.add((byte) globals.size());
        for (Global global : globals) {
            bytes.add(global.type().getIdx());
            bytes.add(global.mutability());
            for (byte curByte : global.expression()) {
                bytes.add(curByte);
            }
        }
    }
}
