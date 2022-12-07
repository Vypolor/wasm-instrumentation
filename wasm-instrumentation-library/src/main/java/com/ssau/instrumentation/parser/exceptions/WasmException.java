package com.ssau.instrumentation.parser.exceptions;

public class WasmException extends RuntimeException {
    public WasmException(String message){
        super(message);
    }
}
