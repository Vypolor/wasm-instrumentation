package com.ssau.instrumentation.model;

import com.ssau.instrumentation.model.section.ImportSectionEntryType;

public class Import {

    final String module;
    final String field;
    final ImportSectionEntryType importSectionEntry;

    public Import(String module, String field, ImportSectionEntryType importSectionEntry) {
        this.module = module;
        this.field = field;
        this.importSectionEntry = importSectionEntry;
    }

    @Override
    public String toString() {
        return
                module + '.' + field + ' ' + importSectionEntry;
    }
}
