package com.ssau.instrumentation.parser.readers.section.impl;

import com.ssau.instrumentation.model.Global;
import com.ssau.instrumentation.parser.Handler;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;

import java.util.ArrayList;

public class GlobalSectionReader extends BaseSectionReader<Global> {

    public GlobalSectionReader(WasmReader file, int length) {
        super(file, length);
    }

    @Override
    protected Global read() {
        var type = file.type();
        var mutability = file.readU8();
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

        return new Global(type, mutability, expressionBuffer);
    }

    @Override
    public void handle(Handler handler) {
        handler.onGlobalSection(this);
    }
}
