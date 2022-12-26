package com.ssau.instrumentation.parser.writers;

import com.ssau.instrumentation.handler.Module;
import com.ssau.instrumentation.model.enums.SectionType;
import com.ssau.instrumentation.parser.writers.impl.*;

public class SectionWritersFactory {

    public SectionWriter getSectionWriter(SectionType type, int sectionLength, Module module) {
        SectionWriter sectionWriter = null;
        switch (type) {
            case Type -> sectionWriter = new TypeSectionWriter(sectionLength, module.getTypes());
            case Import -> sectionWriter = new ImportSectionWriter();
            case Function -> sectionWriter = new FunctionSectionWriter(sectionLength, module.getFunctionTypes());
            case Table -> sectionWriter = new TableSectionWriter(sectionLength, module.getTables());
            case Memory -> sectionWriter = new MemorySectionWriter(sectionLength, module.getMemories());
            case Global -> sectionWriter = new GlobalSectionWriter(sectionLength, module.getGlobals());
            case Export -> sectionWriter = new ExportSectionWriter(sectionLength, module.getExportSegments());
            default -> System.out.println("Can't find writer for specified section");
        }
        return sectionWriter;
    }
}
