package com.ssau.instrumentation.parser.readers.section.impl;

import com.ssau.instrumentation.model.enums.ExternalKind;
import com.ssau.instrumentation.model.segments.ExportSegment;
import com.ssau.instrumentation.parser.Handler;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;

public class ExportSectionReader extends BaseSectionReader<ExportSegment> {

    public ExportSectionReader(WasmReader file, int length) {
        super(file, length);
    }

    @Override
    protected ExportSegment read() {
        String name = file.readString();
        byte importCode = file.readU8();
        ExternalKind kind = ExternalKind.valueOf(importCode);
        int index = file.readUnsignedLeb128();
        return new ExportSegment(name, kind, index);
    }

    @Override
    public void handle(Handler handler) {
        handler.onExportSection(this);
    }
}
