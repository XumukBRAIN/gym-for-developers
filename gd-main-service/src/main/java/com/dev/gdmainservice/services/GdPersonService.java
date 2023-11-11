package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.ExceptionConst;
import com.dev.gdmainservice.exceptions.GdNotFoundException;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdPerson;
import com.dev.gdmainservice.repositories.GdPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с пользоваетлем
 *
 * @author Ildar
 */
@Service
public class GdPersonService {
    private final GdPersonRepository gdPersonRepository;

    @Autowired
    public GdPersonService(GdPersonRepository gdPersonRepository) {
        this.gdPersonRepository = gdPersonRepository;
    }

    /**
     * Метод для создания пользователя
     *
     * @param gdPerson Данные пользователя
     */
    public void save(GdPerson gdPerson) {
        if (gdPerson == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        gdPersonRepository.save(gdPerson);
    }

    /**
     * Метод для поиска пользователя по id
     *
     * @param id Идентификатор пользователя
     */
    public GdPerson findOne(Long id) {
        return gdPersonRepository.findById(id)
                .orElseThrow(() -> new GdNotFoundException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF));
    }

}
