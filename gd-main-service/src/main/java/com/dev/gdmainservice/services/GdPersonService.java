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
    private final GdPersonRepository personRepository;

    @Autowired
    public GdPersonService(GdPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Метод для создания пользователя
     *
     * @param person Данные пользователя
     */
    public void save(GdPerson person) {
        if (person == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }

        personRepository.save(person);
    }

    /**
     * Метод для поиска пользователя по id
     *
     * @param id Идентификатор пользователя
     */
    public GdPerson findOne(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new GdNotFoundException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF));
    }

    /**
     * Метод для получения дополнительной информации о пользователе
     *
     * @param id Идентификатор пользователя
     * @return Дополнительная информация
     */
    public GdPerson.ExtraInfo getExtraInfo(Long id) {
        if (id == null) {
            throw new GdRuntimeException("Не передан идентификатор пользователя", "personService.getExtraInfo.id.null");
        }

        return personRepository.getExtraInfoById(id);
    }
}
