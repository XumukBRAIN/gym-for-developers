package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.GdNotFoundException;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdPerson;
import com.dev.gdmainservice.repositories.GdPersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с пользоваетлем
 *
 * @author Ildar
 */
@Slf4j
@Service
public class GdPersonService {
    private final GdPersonRepository personRepository;

    ObjectMapper objectMapper = new ObjectMapper();

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
            throw new GdRuntimeException("В качестве параметра был передан null");
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
                .orElseThrow(() -> new GdNotFoundException("Пользователь с заданным идентификатором на найден"));
    }

    /**
     * Метод для получения дополнительной информации о пользователе
     *
     * @param id Идентификатор пользователя
     * @return Дополнительная информация
     */
    public GdPerson.ExtraInfo getExtraInfo(Long id) {
        if (id == null) {
            throw new GdRuntimeException("Не передан идентификатор пользователя");
        }

        return personRepository.getExtraInfoById(id);
    }

    /**
     * Метод для создания/обновления дополнительной информации
     *
     * @param id Идентификатор пользователя
     * @param extraInfo Дополнительная информация
     */
    public void saveExtraInfo(Long id, GdPerson.ExtraInfo extraInfo) {
        if (extraInfo == null || id == null) {
            throw new GdRuntimeException("Невалидные параметры при запросе");
        }

        try {
            String extraInfoJson = objectMapper.writeValueAsString(extraInfo);
            personRepository.saveExtraInfo(id, extraInfoJson);
            log.info("Дополнительная информация о пользователе создана/обновлена успешно");
        } catch (JsonProcessingException e) {
            log.error("Ошибка конвертации: {}", e.getMessage());
            throw new GdRuntimeException("Ошибка конвертации");
        } catch (Exception e) {
            log.error("Ошибка в ходе выполнения создания/обновления дополнительной информации о пользователе: {}", e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе выполнения создания/обновления дополнительной информации о пользователе");
        }
    }
}
