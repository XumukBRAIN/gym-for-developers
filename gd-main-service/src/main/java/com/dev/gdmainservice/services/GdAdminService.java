package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.ExceptionConst;
import com.dev.gdmainservice.exceptions.GdNotFoundException;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdAdmin;
import com.dev.gdmainservice.repositories.GdAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param gdAdmin Данные администратора
     */
    public void save(GdAdmin gdAdmin) {

        if (gdAdmin == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }

        adminRepository.save(gdAdmin);
    }

    /**
     * Метод для поиска админа по имени
     *
     * @param name Имя администратора
     */
    public GdAdmin findByName(String name) {
        return adminRepository.findByName(name)
                .orElseThrow(() -> new GdNotFoundException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF));
    }
}
