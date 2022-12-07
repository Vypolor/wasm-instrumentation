package com.ssau.instrumentation.parser;

import com.ssau.instrumentation.parser.readers.BufferReader;
import com.ssau.instrumentation.parser.readers.section.impl.*;

public interface Handler {
    void onVersion(byte[] version);

    void onCustomSection(BufferReader buffer);

    void onStart(int i);

    void onTypeSection(TypeSectionReader reader);

    void onDataSection(DataSectionReader reader);

    void onExportSection(ExportSectionReader reader);

    void onFunctionSection(FunctionSectionReader reader);

    void onGlobalSection(GlobalSectionReader reader);

    void onImportSection(ImportSectionReader reader);

    void onMemorySection(MemorySectionReader reader);

    void onTableSection(TableSectionReader reader);

    void onFunction(CodeSectionReader reader);

    void onElementSection(ElementSectionReader reader);
}
