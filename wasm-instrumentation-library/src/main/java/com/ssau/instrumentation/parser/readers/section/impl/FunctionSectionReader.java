package com.ssau.instrumentation.parser.readers.section.impl;

import com.ssau.instrumentation.parser.Handler;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;

public class FunctionSectionReader extends BaseSectionReader<Integer> {

    public FunctionSectionReader(WasmReader file, int length) {
        super(file, length);
    }

    @Override
    protected Integer read() {
        return file.readUnsignedLeb128();
    }

    @Override
    public void handle(Handler handler) {
        handler.onFunctionSection(this);
    }
}
