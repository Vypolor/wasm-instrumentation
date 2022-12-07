package com.ssau.instrumentation.model.segments;

public class ElementSegment {
    private final int tableidx;
    private final byte[] expression;
    private final int[] funcidxs;

    public ElementSegment(int tableidx, byte[] expression, int[] funcidxs) {
        this.tableidx = tableidx;
        this.expression = expression;
        this.funcidxs = funcidxs;
    }
}
