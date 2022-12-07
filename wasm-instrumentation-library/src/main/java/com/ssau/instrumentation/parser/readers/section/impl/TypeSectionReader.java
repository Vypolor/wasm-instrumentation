package com.ssau.instrumentation.parser.readers.section.impl;

import com.ssau.instrumentation.model.TypeDefinition;
import com.ssau.instrumentation.model.enums.Type;
import com.ssau.instrumentation.parser.Handler;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;

public class TypeSectionReader extends BaseSectionReader<TypeDefinition> {

    public TypeSectionReader(WasmReader file, int length) {
        super(file, length);
    }

    @Override
    protected TypeDefinition read() {
        byte typeType = file.readU8();
        switch (typeType) {
            case 0x60:
                return readFunctionType();
            case 0x61:
                return readModuleType();
            case 0x62:
                return readInstanceType();
        }
        return null;
    }

    private TypeDefinition readInstanceType() {
        throw new UnsupportedOperationException();
    }

    private TypeDefinition readModuleType() {
        throw new UnsupportedOperationException();
    }

    private TypeDefinition.FunctionType readFunctionType() {
        int parameterCount = file.readUnsignedLeb128();
        Type[] parameterTypes = new Type[parameterCount];
        for (int j = 0; j < parameterCount; j++)
            parameterTypes[j] = file.type();

        int resultCount = file.readUnsignedLeb128();
        Type[] resultTypes = new Type[resultCount];
        for (int j = 0; j < resultCount; j++)
            resultTypes[j] = file.type();

        return new TypeDefinition.FunctionType(parameterTypes, resultTypes);
    }

    @Override
    public void handle(Handler handler) {
        handler.onTypeSection(this);
    }
}
