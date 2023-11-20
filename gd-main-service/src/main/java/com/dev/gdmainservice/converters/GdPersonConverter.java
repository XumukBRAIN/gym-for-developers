package com.dev.gdmainservice.converters;

import com.dev.gdmainservice.exceptions.GdRuntimeException;
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
        if (personDto == null) {
            throw new GdRuntimeException("gdMainService.converters.convertToEntity", "Ошибка при конвертации в сущность, DTO равняется null");
        }

        return GdPerson.builder()
                .age(personDto.getAge())
                .country(personDto.getCountry())
                .email(personDto.getEmail())
                .birthDate(personDto.getBirthDate())
                .name(personDto.getName())
                .extraInfo(convertToExtraInfo(personDto.getExtraInfoDto()))
                .build();
    }

    /**
     * из Entity в DTO
     */
    public static GdPersonDto convertToPersonDTO(GdPerson person) {
        if (person == null) {
            throw new GdRuntimeException("gdMainService.converters.convertToPersonDTO", "Ошибка при конвертации в DTO, сущность равянется null");
        }

        return GdPersonDto.builder()
                .age(person.getAge())
                .name(person.getName())
                .birthDate(person.getBirthDate())
                .email(person.getEmail())
                .country(person.getCountry())
                .extraInfoDto(convertToExtraInfoDTO(person.getExtraInfo()))
                .build();
    }

    public static GdPersonDto.ExtraInfoDto convertToExtraInfoDTO(GdPerson.ExtraInfo extraInfo) {
        if (extraInfo == null) {
            return null;
        }

        return GdPersonDto.ExtraInfoDto.builder()
                .github(extraInfo.getGithub())
                .vk(extraInfo.getVk())
                .instagram(extraInfo.getInstagram())
                .telegram(extraInfo.getTelegram())
                .build();
    }

    public static GdPerson.ExtraInfo convertToExtraInfo(GdPersonDto.ExtraInfoDto extraInfoDto) {
        if (extraInfoDto == null) {
            return null;
        }

        return GdPerson.ExtraInfo.builder()
                .github(extraInfoDto.getGithub())
                .vk(extraInfoDto.getVk())
                .instagram(extraInfoDto.getInstagram())
                .telegram(extraInfoDto.getTelegram())
                .build();
    }
}