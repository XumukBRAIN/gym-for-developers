package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.ExceptionConst;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdAnswer;
import com.dev.gdmainservice.models.entity.GdQuestion;
import com.dev.gdmainservice.repositories.GdAnswerRepository;
import com.dev.gdmainservice.repositories.GdQuestionRepository;
import com.dev.gdmainservice.utils.AppStartedListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с ответами
 *
 * @author Ildar
 */

@Service
public class GdAnswerService {
    private final GdAnswerRepository answerRepository;
    private final GdQuestionRepository questionRepository;

    @Autowired
    public GdAnswerService(GdAnswerRepository answerRepository, GdQuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    /**
     * Метод для создания ответа
     *
     * @param answer Данные ответа
     */
    public void save(Long questionId, GdAnswer answer) {
        if (answer == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }

        GdQuestion question = questionRepository.findById(questionId);
        if (question == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF);
        }

        List<GdAnswer> answerList = question.getAnswer();

        answerList.add(answer);
        answer.setQuestion(question);

        questionRepository.save(question);
        answerRepository.save(answer);
    }

    /**
     * Лайк ответа
     *
     * @param id Идентификатор ответа
     * @return Текущее количество лайков
     */
    public Long like(Long id) {
        Long currentLikes = AppStartedListener.mapLikes.get(id);
        if (currentLikes == null) {
            currentLikes = 0L;
        }
        Long finalCurrentLikes = currentLikes;

        return AppStartedListener.mapLikes.compute(id, (a, b) -> Math.addExact(finalCurrentLikes, 1));
    }
}

