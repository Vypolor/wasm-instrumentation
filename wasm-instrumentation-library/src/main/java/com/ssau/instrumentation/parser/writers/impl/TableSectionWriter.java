package com.ssau.instrumentation.parser.writers.impl;

import com.ssau.instrumentation.model.section.impl.Table;
import com.ssau.instrumentation.parser.writers.SectionWriter;

import java.util.List;

public class TableSectionWriter implements SectionWriter {

    private final static byte SECTION_IDX = 0x04;

    private final int sectionLength;
    private final List<Table> tableList;

    public TableSectionWriter(int sectionLength, List<Table> tableList) {
        this.sectionLength = sectionLength;
        this.tableList = tableList;
    }

    @Override
    public void write(List<Byte> bytes) {
        bytes.add(SECTION_IDX);
        bytes.add((byte) sectionLength);
        bytes.add((byte) tableList.size());
        for (Table table : tableList) {
            bytes.add(table.getType().getIdx());
            bytes.add(convertBooleanToByte(table.getLimits().isBounded()));
            bytes.add((byte) table.getLimits().getInitial());
            bytes.add((byte) table.getLimits().getMaximum());
        }
    }
}
