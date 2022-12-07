package com.ssau.instrumentation.parser.readers;

import com.ssau.instrumentation.model.Limits;
import com.ssau.instrumentation.model.enums.ExternalKind;
import com.ssau.instrumentation.model.enums.Type;
import com.ssau.instrumentation.model.section.ImportSectionEntryType;
import com.ssau.instrumentation.model.section.impl.*;
import com.ssau.instrumentation.model.section.impl.Module;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class WasmReader extends BufferReader {

    public WasmReader(ByteBuffer buffer) {
        super(buffer);
    }

    public byte[] magic() {
        byte[] buf = new byte[4];
        getBuffer().get(buf, 0, 4);
        return buf;
    }

    public byte[] version() {
        byte[] buf = new byte[4];
        getBuffer().get(buf, 0, 4);
        return buf;
    }

    public byte sectionIdx() {
        return this.readU7();
    }

    public Type type() {
        return Type.valueOf(this.readU7());
    }

    public Limits readLimits() {
        var flags = this.readU8();
        var bounded = flags == 0x01;
        var initial = this.readUnsignedLeb128();
        if (bounded) {
            var maximum = this.readUnsignedLeb128();
            return new Limits(initial, maximum);
        } else {
            return new Limits(initial);
        }
    }

    public ImportSectionEntryType readExternalKind() {
        var importCode = this.readU8();
        var externalKind = ExternalKind.valueOf(importCode);
        switch (externalKind) {
            case Function:
                return new Function(this.readUnsignedLeb128());
            case Table:
                return new Table(this.type(), this.readLimits());
            case Memory:
                return new Memory(this.readLimits());
            case Global:
                return new Global(this.type(), this.readU8() != 0x00);
            case Module:
                return new Module(this.readUnsignedLeb128());
            case Instance:
                return new Instance(this.readUnsignedLeb128());
        }
        return null;
    }

    public final String readString() {
        int length = readUnsignedLeb128();
        byte[] buffer = new byte[length];
        getBuffer().get(buffer, 0, length);
        return new String(buffer, StandardCharsets.UTF_8);
    }
}
