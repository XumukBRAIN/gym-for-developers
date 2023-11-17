package com.dev.gdmainservice.services;

import com.dev.gdmainservice.enums.NoteStatus;
import com.dev.gdmainservice.exceptions.GdNotFoundException;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdNote;
import com.dev.gdmainservice.repositories.GdNoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.dev.gdmainservice.exceptions.ExceptionConst.*;

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

    private final Map<String, Consumer<Integer>> CHANGE_STATUS_MAP = Map.of(
            NoteStatus.ACCEPTED.getTitle(), this::accept,
            NoteStatus.REJECTED.getTitle(), this::reject,
            NoteStatus.DELETED.getTitle(), this::delete,
            NoteStatus.RECOVERED.getTitle(), this::recover
    );

    /**
     * Метод для создания заметки
     *
     * @param note Критерии заметки
     */
    public void save(GdNote note) {
        if (note == null) {
            throw new GdRuntimeException(NULL_PARAM_MSG, NULL_PARAM_CODE);
        }

        note.setStatus(NoteStatus.IN_REVIEW.getTitle());
        noteRepository.save(note);
    }

    /**
     * Метод для поиска заметки по id
     *
     * @param id Идентификатор заметки
     */
    public GdNote findOne(int id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new GdNotFoundException(NOT_FOUND_MSG, NOT_FOUND_CODE));
    }

    /**
     * Метод для получения списка заметок в статусе 'Рассмотрение'
     *
     * @param status Статус
     * @return Список заметок
     */
    public List<GdNote> getAllReviewingNotes(String status) {
        if (StringUtils.isBlank(status)) {
            log.warn("Был указан несуществующий статус");
            return Collections.emptyList();
        }

        try {
            return noteRepository.findAllByStatusEquals(NoteStatus.IN_REVIEW.getTitle());
        } catch (Exception e) {
            log.error("Ошибка во время получения заметок в статусе {}: {}", NoteStatus.IN_REVIEW.getTitle(), e.getMessage());
            throw e;
        }
    }

    /**
     * Метод для смены статусы заметки
     *
     * @param id     Идентификатор заметки
     * @param status Новый статус
     */
    public void changeStatus(Integer id, String status) {
        CHANGE_STATUS_MAP.get(status).accept(id);
    }

    /**
     * Метод принятия заметки
     *
     * @param id Идентификатор заметки
     */
    private void accept(Integer id) {
        try {
            noteRepository.accept(id, NoteStatus.ACCEPTED.getTitle());
        } catch (Exception e) {
            log.error("Ошибка в ходе принятия заметки с идентификатором {}: {}", id, e.getMessage());
            throw e;
        }
    }

    /**
     * Метод отклонения заметки
     *
     * @param id Идентификатор заметки
     */
    private void reject(Integer id) {
        try {
            noteRepository.reject(id, NoteStatus.REJECTED.getTitle());
        } catch (Exception e) {
            log.error("Ошибка в ходе отклонения заметки с идентификатором {}: {}", id, e.getMessage());
            throw e;
        }
    }

    /**
     * Метод удаления заметки
     *
     * @param id Идентификатор заметки
     */
    private void delete(Integer id) {
        try {
            noteRepository.delete(id, NoteStatus.DELETED.getTitle());
        } catch (Exception e) {
            log.error("Ошибка в ходе удаления заметки с идентификатором {}: {}", id, e.getMessage());
            throw e;
        }
    }

    /**
     * Метод восстановления заметки
     *
     * @param id Идентификатор заметки
     */
    private void recover(Integer id) {
        try {
            noteRepository.recover(id, NoteStatus.RECOVERED.getTitle());
        } catch (Exception e) {
            log.error("Ошибка в ходе восстановления заметки с идентификатором {}: {}", id, e.getMessage());
            throw e;
        }
    }
}
