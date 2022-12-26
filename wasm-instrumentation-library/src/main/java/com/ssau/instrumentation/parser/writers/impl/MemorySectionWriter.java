package com.ssau.instrumentation.parser.writers.impl;

import com.ssau.instrumentation.model.Limits;
import com.ssau.instrumentation.model.segments.MemorySegment;
import com.ssau.instrumentation.parser.writers.SectionWriter;

import java.util.List;

public class MemorySectionWriter implements SectionWriter {

    private final static byte SECTION_IDX = 0x05;

    private final int sectionLength;
    private final List<MemorySegment> memorySegments;

    public MemorySectionWriter(int sectionLength, List<MemorySegment> memorySegments) {
        this.sectionLength = sectionLength;
        this.memorySegments = memorySegments;
    }

    @Override
    public void write(List<Byte> bytes) {
        bytes.add(SECTION_IDX);
        bytes.add((byte) sectionLength);
        bytes.add((byte) memorySegments.size());
        for (MemorySegment memorySegment : memorySegments) {
            Limits limits = memorySegment.limits();
            bytes.add(convertBooleanToByte(limits.isBounded()));
            int sectionSize = convert128values(limits.getInitial());
            if (sectionSize > 0) {
                bytes.add((byte) (limits.getInitial() / 2));
                bytes.add((byte) sectionSize);
            } else {
                bytes.add((byte) limits.getInitial());
            }
            sectionSize = convert128values(limits.getMaximum());
            if (sectionSize > 0) {
                bytes.add((byte) (limits.getMaximum() / 2));
                bytes.add((byte) sectionSize);
            } else {
                bytes.add((byte) limits.getMaximum());
            }
        }
    }
}
