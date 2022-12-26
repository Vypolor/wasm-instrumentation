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

    default int convert128values (int number) {
        if (number % 128 == 0) {
            return number / 128;
        }
        return 0;
    }
}
