package com.dev.gdmainservice.converters;

import com.dev.gdmainservice.models.dto.GdAdminDto;
import com.dev.gdmainservice.models.entity.GdAdmin;
import org.springframework.stereotype.Service;

/**
 * Сервис для конвертации Entity в DTO и наоборот(Администратор)
 *
 * @author Ildar
 */
@Service
public class GdAdminConverter {
    /**
     * Из DTO в Entity
     */
    public static GdAdmin convertToEntity(GdAdminDto adminDto) {
        return GdAdmin.builder()
                .name(adminDto.getName())
                .age(adminDto.getAge())
                .birthDate(adminDto.getBirthDate())
                .email(adminDto.getEmail())
                .birthDate(adminDto.getBirthDate())
                .phoneNumber(adminDto.getPhoneNumber())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public static GdAdminDto convertToDTO(GdAdmin admin) {
        return GdAdminDto.builder()
                .name(admin.getName())
                .age(admin.getAge())
                .birthDate(admin.getBirthDate())
                .email(admin.getEmail())
                .phoneNumber(admin.getPhoneNumber())
                .build();
    }
}
