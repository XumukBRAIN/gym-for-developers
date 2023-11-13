package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.ExceptionConst;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdNoteComment;
import com.dev.gdmainservice.models.entity.GdNote;
import com.dev.gdmainservice.repositories.GdNoteCommentRepository;
import com.dev.gdmainservice.repositories.GdNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для работы с комментариями
 *
 * @author Ildar
 */
@Service
public class GdNoteCommentService {
    private final GdNoteCommentRepository commentRepository;
    private final GdNoteRepository noteRepository;

    @Autowired
    public GdNoteCommentService(GdNoteCommentRepository commentRepository, GdNoteRepository noteRepository) {
        this.commentRepository = commentRepository;
        this.noteRepository = noteRepository;
    }

    /**
     * Метод для создания комментария
     *
     * @param noteId  Идентификатор заметки
     * @param comment комментарий
     */
    @Transactional
    public GdNoteComment create(Integer noteId, GdNoteComment comment) {
        if (comment == null) {
            throw new GdRuntimeException("В качестве комментария был передан null", ExceptionConst.ERRORS_CODE_RT);
        }

        GdNote note = noteRepository.findNoteById(noteId);
        if (note == null) {
            throw new GdRuntimeException("В поле noteId был передан null", ExceptionConst.ERRORS_CODE_RT);
        }

        comment.setNote(note);
        commentRepository.save(comment);

        return comment;
    }
}
