package com.ssau.instrumentation.model.segments;

import com.ssau.instrumentation.model.Local;

import java.util.Arrays;

public class FunctionSegment {
    private final Local[] locals;
    private final byte[] opcodes;
    private final int functionLength;

    public FunctionSegment(Local[] locals, byte[] opcodes, int functionLength) {
        this.locals = locals;
        this.opcodes = opcodes;
        this.functionLength = functionLength;
    }

    public Local[] getLocals() {
        return locals;
    }

    public byte[] opcodes() {
        return this.opcodes;
    }

    @Override
    public String toString() {
        return Arrays.toString(locals);
    }

    public int getFunctionLength() {
        return functionLength;
    }
}
