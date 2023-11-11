package com.dev.gdmainservice.models.dto;

import com.dev.gdmainservice.models.entity.GdQuestion;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class GdAnswerDto {
    private Long questionId;

    private String response;

    private GdQuestion question;

    private String whoAnswered;

    private Long likes;
}
