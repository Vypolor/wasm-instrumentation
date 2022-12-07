package com.ssau.instrumentation.model;

import com.ssau.instrumentation.model.enums.Type;

public class Element {

    final Kind kind;
    final Type type;

    public Element(Kind kind, Type type) {
        this.kind = kind;
        this.type = type;
    }
}
