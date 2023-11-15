package com.dev.gdmainservice.converters;

import com.dev.gdmainservice.models.dto.GdNoteCommonDto;
import com.dev.gdmainservice.models.entity.GdNote;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Сервис для конвертации Entity в DTO и наоборот(Заметка)
 *
 * @author Ildar
 */
@Service
public class GdNoteConverter {
    /**
     * Из DTO в Entity
     */
    public static GdNote convertToEntity(GdNoteCommonDto noteDto) {
        return GdNote.newBuilder()
                .advice(noteDto.getAdvice())
                .whoCreated(noteDto.getWhoCreated())
                .section(noteDto.getSection())
                .dateOfCreation(LocalDate.now())
                .status(noteDto.getStatus())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public static GdNoteCommonDto convertToDTO(GdNote note) {
        return GdNoteCommonDto.newBuilderDTO()
                .dataOfCreation(LocalDate.now())
                .advice(note.getAdvice())
                .section(note.getSection())
                .whoCreated(note.getWhoCreated())
                .status(note.getStatus())
                .build();
    }
}
