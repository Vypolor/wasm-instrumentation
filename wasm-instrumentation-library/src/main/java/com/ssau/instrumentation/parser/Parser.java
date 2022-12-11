package com.ssau.instrumentation.parser;

import com.ssau.instrumentation.model.enums.SectionType;
import com.ssau.instrumentation.parser.exceptions.ParseException;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Parser {

    private final static byte[] ASM = {0x00, 0x61, 0x73, 0x6D};

    public void parse(ByteBuffer buffer, Handler handler) {
        WasmReader file = new WasmReader(buffer);
        checkAsmBytes(file);

        byte[] version = file.version();
        handler.onVersion(version);
        try {
            while (true) {
                byte sectionIdx = file.sectionIdx();
                int sectionLength = file.readUnsignedLeb128();

                if (sectionIdx == SectionType.CUSTOM_IDX) {
                    handler.onCustomSection(file.slice(sectionLength));
                    file.skip(sectionLength);
                } else if (sectionIdx == SectionType.START_IDX) {
                    handler.onStart(file.readUnsignedLeb128());
                } else if (SectionType.isValidSectionIdx(sectionIdx)) {
                    SectionType sectionType = SectionType.valueOf(sectionIdx);
                    BaseSectionReader<Object> reader = sectionType.newReader(file, sectionLength);
                    reader.handle(handler);
                    reader.drain();
                }

            }
        } catch (BufferUnderflowException e) {
            return;
        }
    }

    private void checkAsmBytes(WasmReader file) {
        var magic = file.magic();
        if (!Arrays.equals(magic, ASM)) {
            throw new ParseException("Invalid magic");
        }
    }
}
