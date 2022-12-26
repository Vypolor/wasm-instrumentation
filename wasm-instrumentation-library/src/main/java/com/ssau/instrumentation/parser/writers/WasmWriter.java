package com.ssau.instrumentation.parser.writers;

import com.google.common.io.Files;
import com.google.common.primitives.Bytes;
import com.ssau.instrumentation.handler.Module;
import com.ssau.instrumentation.parser.writers.section.info.SectionInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WasmWriter {

    private final static byte[] ASM = {0x00, 0x61, 0x73, 0x6D};
    private final static byte[] VERSION = {0x01, 0x00, 0x00, 0x00};

    private final Module module;
    private final String outputFilesPath;
    private final List<SectionInfo> sectionInfos;

    public WasmWriter(Module module, String outputFilePath, List<SectionInfo> sectionInfos) {
        this.module = module;
        this.outputFilesPath = outputFilePath;
        this.sectionInfos = sectionInfos;
    }

    public void saveWasmModuleToFile() throws IOException {
        List<Byte> bytesArray = new ArrayList<>();
        writeMagicNumbers(bytesArray, ASM);
        writeMagicNumbers(bytesArray, VERSION);

        List<SectionWriter> sectionWriters = collectSectionWriters();
        for (SectionWriter writer : sectionWriters) {
            writer.write(bytesArray);
        }
        byte[] result = Bytes.toArray(bytesArray);
        Files.write(result, new File(outputFilesPath));
    }


    private List<SectionWriter> collectSectionWriters() {
        List<SectionWriter> sectionWriters = new ArrayList<>();
        SectionWritersFactory sectionWritersFactory = new SectionWritersFactory();
        for (SectionInfo sectionInfo : sectionInfos) {
            sectionWriters.add(sectionWritersFactory
                    .getSectionWriter(sectionInfo.getSectionType()
                            , sectionInfo.getSectionLength(), module));
        }
        return sectionWriters;
    }

    private void writeMagicNumbers(List<Byte> bytes, byte[] magicNumbers) {
        for (int i = 0; i < magicNumbers.length; ++i) {
            bytes.add(magicNumbers[i]);
        }
    }
}
