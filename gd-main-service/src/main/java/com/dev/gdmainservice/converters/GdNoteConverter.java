package com.dev.gdmainservice.converters;

import com.dev.gdmainservice.models.dto.GdNoteDto;
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
    public GdNote convertToEntity(GdNoteDto gdNoteDTO) {
        return GdNote.newBuilder()
                .advice(gdNoteDTO.getAdvice())
                .whoCreated(gdNoteDTO.getWhoCreated())
                .section(gdNoteDTO.getSection())
                .dateOfCreation(LocalDate.now())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdNoteDto convertToDTO(GdNote gdNote) {
        return GdNoteDto.newBuilderDTO()
                .dataOfCreation(LocalDate.now())
                .advice(gdNote.getAdvice())
                .section(gdNote.getSection())
                .whoCreated(gdNote.getWhoCreated())
                .build();
    }

}
