package com.dev.gdmainservice.converters;

import com.dev.gdmainservice.models.dto.GdPersonDto;
import com.dev.gdmainservice.models.entity.GdPerson;
import org.springframework.stereotype.Service;

/**
 * Сервис для конвертации Entity в DTO и наоборот(пользователь)
 *
 * @author Ildar
 */
@Service
public class GdPersonConverter {
    /**
     * Из DTO в Entity
     */
    public static GdPerson convertToEntity(GdPersonDto personDto) {
        return GdPerson.builder()
                .age(personDto.getAge())
                .country(personDto.getCountry())
                .email(personDto.getEmail())
                .birthDate(personDto.getBirthDate())
                .name(personDto.getName())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public static GdPersonDto convertToPersonDTO(GdPerson person) {
        return GdPersonDto.builder()
                .age(person.getAge())
                .name(person.getName())
                .birthDate(person.getBirthDate())
                .email(person.getEmail())
                .country(person.getCountry())
                .build();
    }
}