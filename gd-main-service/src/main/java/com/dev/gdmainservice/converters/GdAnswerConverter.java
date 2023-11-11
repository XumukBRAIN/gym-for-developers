package com.dev.gdmainservice.converters;

import com.dev.gdmainservice.models.dto.GdAnswerDto;
import com.dev.gdmainservice.models.entity.GdAnswer;
import org.springframework.stereotype.Service;

/**
 * Сервис для конвертации Entity в DTO и наоборот(Ответ)
 *
 * @author Ildar
 */
@Service
public class GdAnswerConverter {
    /**
     * Из DTO в Entity
     */
    public static GdAnswer convertToEntity(GdAnswerDto answerDto) {
        return GdAnswer.builder()
                .response(answerDto.getResponse())
                .question(answerDto.getQuestion())
                .whoAnswered(answerDto.getWhoAnswered())
                .likes(answerDto.getLikes())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public static GdAnswerDto convertToDto(GdAnswer answer) {
        return GdAnswerDto.builder()
                .response(answer.getResponse())
                .question(answer.getQuestion())
                .whoAnswered(answer.getWhoAnswered())
                .likes(answer.getLikes())
                .build();
    }
}
