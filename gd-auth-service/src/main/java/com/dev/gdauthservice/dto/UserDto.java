package com.dev.gdauthservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    private String name;

    @Size(min = 5, max = 100, message = "Password must be between 5 and 100 characters")
    private String password;
}
