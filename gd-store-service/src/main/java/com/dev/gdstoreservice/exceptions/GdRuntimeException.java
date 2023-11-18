package com.dev.gdstoreservice.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GdRuntimeException extends RuntimeException {
    private final String message;
    private final LocalDateTime date;

    public GdRuntimeException(String code, String message) {
        super(code);
        this.message = message;
        this.date = LocalDateTime.now();
    }
}
