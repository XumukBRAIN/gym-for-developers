package com.dev.gdmainservice.exceptions;

import lombok.Getter;

@Getter
public class GdNotFoundException extends RuntimeException {

    private final String message;

    public GdNotFoundException(String message) {
        this.message = message;
    }
}
