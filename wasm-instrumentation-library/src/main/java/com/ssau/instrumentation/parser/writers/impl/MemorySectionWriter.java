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
        writeUnsignedLeb128(bytes, sectionLength);
        writeUnsignedLeb128(bytes, memorySegments.size());
        for (MemorySegment memorySegment : memorySegments) {
            Limits limits = memorySegment.limits();
            bytes.add(convertBooleanToByte(limits.isBounded()));
            writeUnsignedLeb128(bytes, limits.getInitial());
            writeUnsignedLeb128(bytes, limits.getMaximum());
        }
    }
}
