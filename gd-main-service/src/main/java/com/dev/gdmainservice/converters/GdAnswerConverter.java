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
    public GdAnswer convertToEntity(GdAnswerDto gdAnswerDTO) {
        return GdAnswer.builder()
                .response(gdAnswerDTO.getResponse())
                .question(gdAnswerDTO.getQuestion())
                .whoAnswered(gdAnswerDTO.getWhoAnswered())
                .likes(gdAnswerDTO.getLikes())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdAnswerDto convertToDto(GdAnswer gdAnswer) {
        return GdAnswerDto.builder()
                .response(gdAnswer.getResponse())
                .question(gdAnswer.getQuestion())
                .whoAnswered(gdAnswer.getWhoAnswered())
                .likes(gdAnswer.getLikes())
                .build();
    }

}
