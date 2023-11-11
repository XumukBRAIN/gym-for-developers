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
    public GdComment convertToEntity(GdCommentDto gdCommentDTO) {
        return GdComment.builder()
                .author(gdCommentDTO.getAuthor())
                .note(gdCommentDTO.getNote())
                .text(gdCommentDTO.getText())
                .dateOfCreation(LocalDateTime.now())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdCommentDto convertToDTO(GdComment gdComment) {
        return GdCommentDto.builder()
                .author(gdComment.getAuthor())
                .text(gdComment.getText())
                .dateOfCreation(gdComment.getDateOfCreation())
                .build();
    }
}
