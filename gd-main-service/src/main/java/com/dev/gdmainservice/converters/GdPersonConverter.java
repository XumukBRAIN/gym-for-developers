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
    public GdPerson convertToEntity(GdPersonDto gdPersonDTO) {
        return GdPerson.builder()
                .age(gdPersonDTO.getAge())
                .country(gdPersonDTO.getCountry())
                .email(gdPersonDTO.getEmail())
                .birthDate(gdPersonDTO.getBirthDate())
                .name(gdPersonDTO.getName())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdPersonDto convertToPersonDTO(GdPerson gdPerson) {
        return GdPersonDto.builder()
                .age(gdPerson.getAge())
                .name(gdPerson.getName())
                .birthDate(gdPerson.getBirthDate())
                .email(gdPerson.getEmail())
                .country(gdPerson.getCountry())
                .build();
    }

}