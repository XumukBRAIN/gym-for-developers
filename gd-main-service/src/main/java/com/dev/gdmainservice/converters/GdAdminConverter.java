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
    public GdAdmin convertToEntity(GdAdminDto gdAdminDTO) {
        return GdAdmin.builder()
                .name(gdAdminDTO.getName())
                .age(gdAdminDTO.getAge())
                .birthDate(gdAdminDTO.getBirthDate())
                .email(gdAdminDTO.getEmail())
                .birthDate(gdAdminDTO.getBirthDate())
                .phoneNumber(gdAdminDTO.getPhoneNumber())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdAdminDto convertToDTO(GdAdmin gdAdmin) {
        return GdAdminDto.builder()
                .name(gdAdmin.getName())
                .age(gdAdmin.getAge())
                .birthDate(gdAdmin.getBirthDate())
                .email(gdAdmin.getEmail())
                .phoneNumber(gdAdmin.getPhoneNumber())
                .build();
    }

}
