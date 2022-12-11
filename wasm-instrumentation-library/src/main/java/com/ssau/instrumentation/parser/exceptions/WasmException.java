package com.ssau.instrumentation.parser.exceptions;

public class WasmException extends RuntimeException {
    //TODO add correct log message
    public WasmException(String message){
        super(message);
    }
}
