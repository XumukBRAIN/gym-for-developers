package com.dev.gdmainservice.models.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class GdPersonDto {
    private String name;

    private String email;

    private Integer age;

    private String country;

    private LocalDate birthDate;
}
