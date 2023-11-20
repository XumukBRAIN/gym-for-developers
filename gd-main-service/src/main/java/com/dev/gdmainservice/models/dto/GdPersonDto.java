package com.dev.gdmainservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class GdPersonDto {
    private String name;

    private String email;

    private Integer age;

    private String country;

    private LocalDate birthDate;

    private GdPersonDto.ExtraInfoDto extraInfoDto;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class ExtraInfoDto {
        private String github;
        private String instagram;
        private String vk;
        private String telegram;
    }
}
