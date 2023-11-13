package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.ExceptionConst;
import com.dev.gdmainservice.exceptions.GdNotFoundException;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdNote;
import com.dev.gdmainservice.repositories.GdNoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с заметками
 *
 * @author Ildar
 */
@Slf4j
@Service
public class GdNoteService {
    private final GdNoteRepository noteRepository;

    @Autowired
    public GdNoteService(GdNoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Метод для создания заметки
     *
     * @param note Критерии заметки
     */
    public void save(GdNote note) {
        if (note == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }

        noteRepository.save(note);
    }

    /**
     * Метод для поиска заметки по id
     *
     * @param id Идентификатор заметки
     */
    public GdNote findOne(int id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new GdNotFoundException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF));
    }

    /**
     * @see GdNoteRepository#changeStatus(Integer, Integer)
     */
    public void changeStatus(Integer id, Integer status) {
        if (id == null) {
            throw new GdRuntimeException("Не указан идентификатор заметки", "noteService.changeStatus.id.null");
        }

        if (status == null) {
            throw new GdRuntimeException("Не указан новый статус заметки", "noteService.changeStatus.status.null");
        }

        try {
            noteRepository.changeStatus(id, status);
            log.info("Статус заметки с идентификатором {} сменен успешно", id);
        } catch (Exception e) {
            log.error("Ошибка при смене статуса заметки с идентификатором {}: {}", id, e.getMessage());
            throw new GdRuntimeException("Ошибка при смене статуса заметки", "noteService.changeStatus.error");
        }
    }
}
