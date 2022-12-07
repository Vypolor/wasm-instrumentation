package com.ssau.instrumentation.model.section;

public abstract class ImportSectionEntryType {

    public boolean isA(Class<? extends ImportSectionEntryType> type) {
        return type.isInstance(this);
    }
}
