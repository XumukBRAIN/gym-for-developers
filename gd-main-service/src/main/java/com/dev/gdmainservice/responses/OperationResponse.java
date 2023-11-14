package com.dev.gdmainservice.responses;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class OperationResponse<T> {
    private final Integer httpStatus;
    private final String message;
    private final T body;
    private final LocalDate date;

    public OperationResponse(Integer httpStatus, String message, T body) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.body = body;
        this.date = LocalDate.now();
    }

    public interface Message {
        String SUCCESS = "SUCCESS";
        String FAILURE = "FAILURE";
    }
}
