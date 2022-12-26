package com.ssau.instrumentation.parser;

import com.ssau.instrumentation.handler.Module;
import com.ssau.instrumentation.model.enums.SectionType;
import com.ssau.instrumentation.parser.exceptions.ParseException;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;
import com.ssau.instrumentation.parser.writers.section.info.SectionInfo;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private final static byte[] ASM = {0x00, 0x61, 0x73, 0x6D};

    public List<SectionInfo> parse(ByteBuffer buffer, Handler handler) {
        List<SectionInfo> resultInfo = new ArrayList<>();
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
                    resultInfo.add(new SectionInfo(sectionType, sectionLength));
                }

            }
        } catch (BufferUnderflowException e) {
            return resultInfo;
        }
    }

    private void checkAsmBytes(WasmReader file) {
        var magic = file.magic();
        if (!Arrays.equals(magic, ASM)) {
            throw new ParseException("Invalid magic");
        }
    }

    public Module parseModule(ByteBuffer buffer, Handler handler) {
        Module module = new Module();
        WasmReader file = new WasmReader(buffer);
        checkAsmBytes(file);

        byte[] version = file.version();
        handler.onVersion(version);

        while (buffer.position() < buffer.limit()) {
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
        return null;
    }
}
