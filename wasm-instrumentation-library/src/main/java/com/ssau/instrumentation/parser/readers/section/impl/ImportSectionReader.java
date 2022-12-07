package com.ssau.instrumentation.parser.readers.section.impl;

import com.ssau.instrumentation.model.Import;
import com.ssau.instrumentation.parser.Handler;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;

public class ImportSectionReader extends BaseSectionReader<Import> {

    public ImportSectionReader(WasmReader file, int length) {
        super(file, length);
    }

    @Override
    protected Import read() {
        var module = file.readString();
        var name = file.readString();
        var sectionEntryType = file.readExternalKind();
        return new Import(module, name, sectionEntryType);
    }

    @Override
    public void handle(Handler handler) {
        handler.onImportSection(this);
    }
}
