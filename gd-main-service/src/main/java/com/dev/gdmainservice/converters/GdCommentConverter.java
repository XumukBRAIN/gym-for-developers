package com.dev.gdmainservice.converters;

import com.dev.gdmainservice.models.dto.GdCommentDto;
import com.dev.gdmainservice.models.entity.GdComment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Сервис для конвертации Entity в DTO и наоборот(Комментарий)
 *
 * @author Ildar
 */
@Service
public class GdCommentConverter {
    /**
     * Из DTO в Entity
     */
    public static GdComment convertToEntity(GdCommentDto commentDto) {
        return GdComment.builder()
                .author(commentDto.getAuthor())
                .note(commentDto.getNote())
                .text(commentDto.getText())
                .dateOfCreation(LocalDateTime.now())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public static GdCommentDto convertToDTO(GdComment comment) {
        return GdCommentDto.builder()
                .author(comment.getAuthor())
                .text(comment.getText())
                .dateOfCreation(comment.getDateOfCreation())
                .build();
    }
}
