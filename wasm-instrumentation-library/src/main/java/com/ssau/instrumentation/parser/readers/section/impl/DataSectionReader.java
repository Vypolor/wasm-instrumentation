package com.ssau.instrumentation.parser.readers.section.impl;

import com.ssau.instrumentation.model.segments.DataSegment;
import com.ssau.instrumentation.parser.Handler;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;

import java.util.ArrayList;
import java.util.List;

public class DataSectionReader extends BaseSectionReader<DataSegment> {

    public DataSectionReader(WasmReader file, int length) {
        super(file, length);
    }

    @Override
    protected DataSegment read() {
        int index = file.readUnsignedLeb128();
        List<Byte> expression = new ArrayList<Byte>();
        byte instruction;
        do {
            instruction = file.readU8();
            expression.add(instruction);
        } while (instruction != 0x0B);
        byte[] expressionBuffer = new byte[expression.size()];
        for (int j = 0; j < expression.size(); j++) {
            expressionBuffer[j] = expression.get(j);
        }
        int dataLength = file.readUnsignedLeb128();
        byte[] dataBuffer = file.readBytes(dataLength);

        return new DataSegment(index, expressionBuffer, dataBuffer);
    }

    @Override
    public void handle(Handler handler) {
        handler.onDataSection(this);
    }
}
