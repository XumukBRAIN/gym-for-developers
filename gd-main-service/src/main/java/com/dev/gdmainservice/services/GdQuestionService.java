package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.ExceptionConst;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdQuestion;
import com.dev.gdmainservice.repositories.GdQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param gdQuestion Данные вопроса
     */
    public void save(GdQuestion gdQuestion) {
        if (gdQuestion == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        questionRepository.save(gdQuestion);
    }
}
