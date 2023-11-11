package com.dev.gdmainservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@AllArgsConstructor
@Data
public class ErrorsResponse {
    String errors;
    String message;
}
