package com.ssau.instrumentation.parser.readers.section.impl;

import com.ssau.instrumentation.model.segments.ElementSegment;
import com.ssau.instrumentation.parser.Handler;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;

import java.util.ArrayList;

public class ElementSectionReader extends BaseSectionReader<ElementSegment> {
    public ElementSectionReader(WasmReader file, int length) {
        super(file, length);
    }

    @Override
    protected ElementSegment read() {
        var tableIndex = file.readI32();
        byte instruction;
        var instructionsList = new ArrayList<Byte>();
        do {
            instruction = file.readU8();
            instructionsList.add(instruction);
        } while (instruction != 0x0B);
        var expressionBuffer = new byte[instructionsList.size()];
        for (int j = 0; j < expressionBuffer.length; j++) {
            expressionBuffer[j] = instructionsList.get(j);
        }
        var funcVecSize = file.readI32();
        var funcIdxs = new int[funcVecSize];
        for (int k = 0; k < funcVecSize; k++) {
            funcIdxs[k] = file.readI32();
        }

        return new ElementSegment(tableIndex, expressionBuffer, funcIdxs);
    }

    @Override
    public void handle(Handler handler) {
        handler.onElementSection(this);
    }
}
