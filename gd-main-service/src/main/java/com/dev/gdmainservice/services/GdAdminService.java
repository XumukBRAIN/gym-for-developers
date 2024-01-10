package com.dev.gdmainservice.services;

import com.dev.gdmainservice.exceptions.GdNotFoundException;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.entity.GdAdmin;
import com.dev.gdmainservice.repositories.GdAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с администратором
 *
 * @author Ildar
 */
@Service
public class GdAdminService {
    @Value("${gym.mail.username}")
    private String username;

    private final GdAdminRepository adminRepository;
    private final MailSender mailSender;

    @Autowired
    public GdAdminService(GdAdminRepository adminRepository, MailSender mailSender) {
        this.adminRepository = adminRepository;
        this.mailSender = mailSender;
    }

    /**
     * Метод для создания администратора
     *
     * @param admin Данные администратора
     */
    public void save(GdAdmin admin) {
        if (admin == null) {
            throw new GdRuntimeException("В качестве параметра был передан null");
        }

        adminRepository.save(admin);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(admin.getEmail());
        mailMessage.setSubject("Регистрация аккаунта");
        mailMessage.setText(
                "Дорогой, " + admin.getName() + ". " +
                        "Мы рады сообщить вам, что регистрация аккаунта админа прошла успешно!"
        );

        mailSender.send(mailMessage);
    }

    /**
     * Метод для поиска админа по имени
     *
     * @param name Имя администратора
     */
    public GdAdmin findByName(String name) {
        return adminRepository.findByName(name)
                .orElseThrow(() -> new GdNotFoundException("Админ с заданным именем не найден"));
    }
}
