package com.ssau.instrumentation.parser.readers.section.impl;

import com.ssau.instrumentation.model.Local;
import com.ssau.instrumentation.model.enums.Type;
import com.ssau.instrumentation.model.segments.FunctionSegment;
import com.ssau.instrumentation.parser.Handler;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;

public class CodeSectionReader extends BaseSectionReader<FunctionSegment> {

    public CodeSectionReader(WasmReader file, int length) {
        super(file, length);
    }

    @Override
    protected FunctionSegment read() {
        var functionLength = file.readUnsignedLeb128();
        var functionStart = file.position();
        var localsCount = file.readUnsignedLeb128();
        var locals = new Local[localsCount];
        for (var l = 0; l < localsCount; l++) {
            var count = file.readUnsignedLeb128();
            var typeIdx = file.readU8();
            var type = Type.valueOf(typeIdx);
            locals[l] = new Local(count, type);
        }
        var functionEnd = file.position();
        var codeLength = functionLength - (functionEnd - functionStart);
        byte[] opcodes = file.readBytes(codeLength);

        return new FunctionSegment(locals, opcodes);
    }

    @Override
    public void handle(Handler handler) {
        handler.onFunction(this);
    }
}