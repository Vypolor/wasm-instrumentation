package com.ssau.instrumentation.parser.writers;

import java.util.List;

public interface SectionWriter {

    void write(List<Byte> bytes);

    default byte convertBooleanToByte(boolean isBounded) {
        if (isBounded) {
            return 0x01;
        } else {
            return 0x00;
        }
    }

    default void writeUnsignedLeb128(List<Byte> out, int value) {
        int remaining = value >>> 7;

        while (remaining != 0) {
            out.add((byte) ((value & 0x7f) | 0x80));
            value = remaining;
            remaining >>>= 7;
        }

        out.add((byte) (value & 0x7f));
    }
}
