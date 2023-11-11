package com.dev.gdmainservice.exceptions;

public interface ExceptionConst {
    String ERRORS_CODE_NF = "NOT_FOUND";
    String MESSAGE_NF = "Данные не были найдены в системе";

    String ERRORS_CODE_RT = "NULL_PARAMETER";
    String MESSAGE_RT = "В качестве параметра был передан null";
}
