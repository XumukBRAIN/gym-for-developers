package com.dev.gdmainservice.responses;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OperationResponse<T> {
    private final Integer httpStatus;
    private final T body;
    private final LocalDateTime date;

    public OperationResponse(Integer httpStatus, T body) {
        this.httpStatus = httpStatus;
        this.body = body;
        this.date = LocalDateTime.now();
    }
}
