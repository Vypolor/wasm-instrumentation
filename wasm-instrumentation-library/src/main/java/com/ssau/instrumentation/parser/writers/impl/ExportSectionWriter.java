package com.ssau.instrumentation.parser.writers.impl;

import com.ssau.instrumentation.model.segments.ExportSegment;
import com.ssau.instrumentation.parser.writers.SectionWriter;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExportSectionWriter implements SectionWriter {

    private final static byte SECTION_IDX = 0x07;

    private final int sectionLength;
    private final List<ExportSegment> exportSegments;

    public ExportSectionWriter(int sectionLength, List<ExportSegment> exportSegments) {
        this.sectionLength = sectionLength;
        this.exportSegments = exportSegments;
    }

    @Override
    public void write(List<Byte> bytes) {
        bytes.add(SECTION_IDX);
        writeUnsignedLeb128(bytes, sectionLength);
        writeUnsignedLeb128(bytes, exportSegments.size());
        for (ExportSegment exportSegment : exportSegments) {
            byte[] name = exportSegment.name().getBytes(StandardCharsets.UTF_8);
            bytes.add((byte) name.length);
            for (byte b : name) {
                bytes.add(b);
            }
            bytes.add((byte) exportSegment.type().ordinal());
            writeUnsignedLeb128(bytes, exportSegment.index());
        }
        System.out.println();
    }
}
