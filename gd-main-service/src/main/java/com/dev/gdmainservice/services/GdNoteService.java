package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.ExceptionConst;
import com.dev.gdmainservice.exceptions.GdNotFoundException;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdNote;
import com.dev.gdmainservice.repositories.GdNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с заметками
 *
 * @author Ildar
 */
@Service
public class GdNoteService {

    private final GdNoteRepository gdNoteRepository;

    @Autowired
    public GdNoteService(GdNoteRepository gdNoteRepository) {
        this.gdNoteRepository = gdNoteRepository;
    }

    /**
     * Метод для создания заметки
     *
     * @param gdNote Критерии заметки
     */
    public void save(GdNote gdNote) {
        if (gdNote == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        gdNoteRepository.save(gdNote);
    }

    /**
     * Метод для поиска заметки по id
     *
     * @param id Идентификатор заметки
     */
    public GdNote findOne(int id) {
        return gdNoteRepository.findById(id)
                .orElseThrow(() -> new GdNotFoundException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF));
    }
}
