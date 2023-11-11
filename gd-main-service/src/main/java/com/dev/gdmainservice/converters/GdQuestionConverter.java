package com.dev.gdmainservice.converters;

import com.dev.gdmainservice.models.dto.GdQuestionDto;
import com.dev.gdmainservice.models.entity.GdQuestion;
import org.springframework.stereotype.Service;

/**
 * Сервис для конвертации Entity в DTO и наоборот(Вопрос)
 *
 * @author Ildar
 */
@Service
public class GdQuestionConverter {
    /**
     * Из DTO в Entity
     */
    public GdQuestion convertToEntity(GdQuestionDto gdQuestionDTO) {
        return GdQuestion.builder()
                .issue(gdQuestionDTO.getIssue())
                .section(gdQuestionDTO.getSection())
                .whoAsked(gdQuestionDTO.getWhoAsked())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdQuestionDto convertToDTO(GdQuestion gdQuestion) {
        return GdQuestionDto.builder()
                .issue(gdQuestion.getIssue())
                .section(gdQuestion.getSection())
                .whoAsked(gdQuestion.getWhoAsked())
                .build();
    }
}
