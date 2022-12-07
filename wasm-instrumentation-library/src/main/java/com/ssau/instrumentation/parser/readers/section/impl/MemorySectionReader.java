package com.ssau.instrumentation.parser.readers.section.impl;

import com.ssau.instrumentation.model.Limits;
import com.ssau.instrumentation.model.segments.MemorySegment;
import com.ssau.instrumentation.parser.Handler;
import com.ssau.instrumentation.parser.readers.WasmReader;
import com.ssau.instrumentation.parser.readers.section.BaseSectionReader;

public class MemorySectionReader extends BaseSectionReader<MemorySegment> {

    public MemorySectionReader(WasmReader file, int length) {
        super(file, length);
    }

    @Override
    protected MemorySegment read() {
        Limits limits = file.readLimits();
        return new MemorySegment(limits);
    }

    @Override
    public void handle(Handler handler) {
        handler.onMemorySection(this);
    }
}
