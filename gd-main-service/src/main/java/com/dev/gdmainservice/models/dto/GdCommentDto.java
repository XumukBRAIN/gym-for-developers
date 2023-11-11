package com.dev.gdmainservice.models.dto;

import com.dev.gdmainservice.models.entity.GdNote;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class GdCommentDto {
    private Integer noteId;

    private String author;

    private GdNote note;

    private LocalDateTime dateOfCreation;

    private String text;
}
