package com.ssau.instrumentation.parser.readers.section.impl;

import com.ssau.instrumentation.model.Limits;
import com.ssau.instrumentation.model.enums.Type;
import com.ssau.instrumentation.model.section.impl.Table;
import com.ssau.instrumentation.parser.Handler;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;

public class TableSectionReader extends BaseSectionReader<Table> {

    public TableSectionReader(WasmReader file, int length) {
        super(file, length);
    }

    @Override
    protected Table read() {
        Type type = file.type();
        Limits limits = file.readLimits();
        return new Table(type, limits);
    }

    @Override
    public void handle(Handler handler) {
        handler.onTableSection(this);
    }
}
