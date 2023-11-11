package com.dev.gdmainservice.models.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class GdAdminDto {
    private String name;

    private Integer age;

    private LocalDate birthDate;

    private String phoneNumber;

    private String email;

}
