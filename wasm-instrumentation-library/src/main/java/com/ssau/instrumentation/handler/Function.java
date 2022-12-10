package com.ssau.instrumentation.handler;

import com.ssau.instrumentation.model.Local;
import com.ssau.instrumentation.model.TypeDefinition;

public class Function {
    private final TypeDefinition.FunctionType type;
    private final Local[] locals;
    private final byte[] opcodes;

    public Function(TypeDefinition.FunctionType type, Local[] locals, byte[] opcodes) {
        this.type = type;
        this.locals = locals;
        this.opcodes = opcodes;
    }

    public byte[] opcodes() {
        return opcodes;
    }

    public Local[] locals() {
        return locals;
    }

    public TypeDefinition.FunctionType type() {
        return type;
    }
}
