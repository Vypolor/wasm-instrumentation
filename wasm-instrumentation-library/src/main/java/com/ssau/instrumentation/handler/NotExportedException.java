package com.ssau.instrumentation.handler;

import com.ssau.instrumentation.parser.exceptions.WasmException;

public class NotExportedException extends WasmException {
    public NotExportedException(String name) {
        super("Not Exported Exception: " + name);
    }
}
