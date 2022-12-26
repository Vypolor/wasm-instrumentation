package com.ssau.instrumentation.parser.writers.impl;

import com.ssau.instrumentation.model.Local;
import com.ssau.instrumentation.model.segments.FunctionSegment;
import com.ssau.instrumentation.parser.writers.SectionWriter;

import java.util.List;

public class CodeSectionWriter implements SectionWriter {

    private final static byte SECTION_IDX = 0x0A;

    private final int sectionLength;
    private final List<FunctionSegment> functionSegments;

    public CodeSectionWriter(int sectionLength, List<FunctionSegment> functionSegments) {
        this.sectionLength = sectionLength;
        this.functionSegments = functionSegments;
    }

    @Override
    public void write(List<Byte> bytes) {
        bytes.add(SECTION_IDX);
        writeUnsignedLeb128(bytes, sectionLength);
        writeUnsignedLeb128(bytes, functionSegments.size());
        for (FunctionSegment functionSegment : functionSegments) {
            writeUnsignedLeb128(bytes, functionSegment.getFunctionLength());
            writeUnsignedLeb128(bytes, functionSegment.getLocals().length);
            Local[] locals = functionSegment.getLocals();
            for (Local local : locals) {
                writeUnsignedLeb128(bytes, local.getCount());
                byte type = local.getType().getIdx();
                bytes.add(type);
            }
            for (byte curByte : functionSegment.opcodes()) {
                bytes.add(curByte);
            }
        }
    }
}
