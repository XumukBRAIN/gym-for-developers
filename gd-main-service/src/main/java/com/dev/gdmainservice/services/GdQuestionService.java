package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdQuestion;
import com.dev.gdmainservice.repositories.GdQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.dev.gdmainservice.exceptions.ExceptionConst.NULL_PARAM_CODE;
import static com.dev.gdmainservice.exceptions.ExceptionConst.NULL_PARAM_MSG;

/**
 * Сервис для работы с вопросами
 *
 * @author Ildar
 */
@Service
public class GdQuestionService {
    private final GdQuestionRepository questionRepository;

    @Autowired
    public GdQuestionService(GdQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Метод для создания вопроса
     *
     * @param question Данные вопроса
     */
    public void save(GdQuestion question) {
        if (question == null) {
            throw new GdRuntimeException(NULL_PARAM_MSG, NULL_PARAM_CODE);
        }

        questionRepository.save(question);
    }
}
