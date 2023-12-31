package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.GdNotFoundException;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdAdmin;
import com.dev.gdmainservice.repositories.GdAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.dev.gdmainservice.exceptions.ExceptionConst.*;

/**
 * Сервис для работы с администратором
 *
 * @author Ildar
 */
@Service
public class GdAdminService {
    private final GdAdminRepository adminRepository;

    @Autowired
    public GdAdminService(GdAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * Метод для создания администратора
     *
     * @param admin Данные администратора
     */
    public void save(GdAdmin admin) {
        if (admin == null) {
            throw new GdRuntimeException(NULL_PARAM_MSG, NULL_PARAM_CODE);
        }

        adminRepository.save(admin);
    }

    /**
     * Метод для поиска админа по имени
     *
     * @param name Имя администратора
     */
    public GdAdmin findByName(String name) {
        return adminRepository.findByName(name)
                .orElseThrow(() -> new GdNotFoundException(NOT_FOUND_MSG, NOT_FOUND_CODE));
    }
}
