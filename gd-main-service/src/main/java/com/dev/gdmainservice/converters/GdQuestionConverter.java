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
    public static GdQuestion convertToEntity(GdQuestionDto questionDto) {
        return GdQuestion.builder()
                .issue(questionDto.getIssue())
                .section(questionDto.getSection())
                .whoAsked(questionDto.getWhoAsked())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public static GdQuestionDto convertToDTO(GdQuestion question) {
        return GdQuestionDto.builder()
                .issue(question.getIssue())
                .section(question.getSection())
                .whoAsked(question.getWhoAsked())
                .build();
    }
}
