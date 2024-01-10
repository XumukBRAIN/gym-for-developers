package com.dev.gdmainservice.exceptions;

import lombok.Getter;

@Getter
public class GdRuntimeException extends RuntimeException {

    private final String message;

    public GdRuntimeException(String message) {
        this.message = message;
    }
}
