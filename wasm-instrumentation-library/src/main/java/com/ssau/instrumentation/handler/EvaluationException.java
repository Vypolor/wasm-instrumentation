package com.ssau.instrumentation.handler;

import com.ssau.instrumentation.parser.exceptions.WasmRuntimeException;

public class EvaluationException extends WasmRuntimeException {
    public EvaluationException(String message) {
        super("Evaluation exception: " + message);
    }
}
