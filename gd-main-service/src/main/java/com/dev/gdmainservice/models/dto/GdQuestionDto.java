package com.dev.gdmainservice.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class GdQuestionDto {

    private String issue;

    private String section;

    private String whoAsked;

}
