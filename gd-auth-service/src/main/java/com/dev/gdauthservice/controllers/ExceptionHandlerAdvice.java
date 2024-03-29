package com.dev.gdauthservice.controllers;

import com.dev.gdauthservice.dto.ErrorDto;
import com.dev.gdauthservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> exceptionHandler(AuthenticationException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorDto(ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> sqlExceptionHandler(SQLException ex) {
        String UNIQUE_VIOLATION = "23505";
        if (ex.getSQLState().equals(UNIQUE_VIOLATION)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDto("User with this name already exists", LocalDateTime.now()));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("Internal service error", LocalDateTime.now()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> userIsNotFoundExceptionHandler(UserNotFoundException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }
}
