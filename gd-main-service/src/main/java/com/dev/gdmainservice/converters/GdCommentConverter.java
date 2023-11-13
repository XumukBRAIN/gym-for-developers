package com.dev.gdmainservice.converters;

import com.dev.gdmainservice.models.dto.GdNoteCommentDto;
import com.dev.gdmainservice.models.entity.GdNoteComment;
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
    public static GdNoteComment convertToEntity(GdNoteCommentDto commentDto) {
        return GdNoteComment.builder()
                .author(commentDto.getAuthor())
                .note(commentDto.getNote())
                .text(commentDto.getText())
                .dateOfCreation(LocalDateTime.now())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public static GdNoteCommentDto convertToDTO(GdNoteComment comment) {
        return GdNoteCommentDto.builder()
                .author(comment.getAuthor())
                .text(comment.getText())
                .dateOfCreation(comment.getDateOfCreation())
                .build();
    }
}
