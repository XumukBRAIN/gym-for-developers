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
    private final GdAnswerRepository gdAnswerRepository;
    private final GdQuestionRepository gdQuestionRepository;

    @Autowired
    public GdAnswerService(GdAnswerRepository gdAnswerRepository, GdQuestionRepository gdQuestionRepository) {
        this.gdAnswerRepository = gdAnswerRepository;
        this.gdQuestionRepository = gdQuestionRepository;
    }

    /**
     * Метод для создания ответа
     *
     * @param gdanswer Данные ответа
     */
    public void save(Long questionId, GdAnswer gdanswer) {

        if (gdanswer == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }

        GdQuestion question = gdQuestionRepository.findById(questionId);
        if (question == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF);
        }

        List<GdAnswer> answerList = question.getAnswer();

        answerList.add(gdanswer);
        gdanswer.setQuestion(question);

        gdQuestionRepository.save(question);
        gdAnswerRepository.save(gdanswer);
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

