package com.ssau.instrumentation.parser.writers.impl;

import com.ssau.instrumentation.model.TypeDefinition;
import com.ssau.instrumentation.model.TypeDefinition.FunctionType;
import com.ssau.instrumentation.model.enums.Type;
import com.ssau.instrumentation.parser.writers.SectionWriter;

import java.util.List;

public class TypeSectionWriter implements SectionWriter {

    private final static byte SECTION_IDX = 0x01;

    private final int sectionLength;
    private final List<TypeDefinition> types;

    public TypeSectionWriter(int sectionLength, List<TypeDefinition> types) {
        this.sectionLength = sectionLength;
        this.types = types;
    }

    @Override
    public void write(List<Byte> bytes) {
        bytes.add(SECTION_IDX);
        bytes.add(((byte) sectionLength));
        bytes.add((byte) types.size());
        for (TypeDefinition typeDefinition : types) {
            bytes.add((byte) 0x60);
            FunctionType functionType = (FunctionType) typeDefinition;
            bytes.add((byte) functionType.parameters().length);
            for (Type type : functionType.parameters()) {
                bytes.add(type.getIdx());
            }
            bytes.add((byte) functionType.returns().length);
            for (Type type : functionType.returns()) {
                bytes.add(type.getIdx());
            }
            System.out.println();
        }
    }
}
