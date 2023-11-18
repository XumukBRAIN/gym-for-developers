package com.dev.gdstoreservice.services;

import com.dev.gdstoreservice.exceptions.GdRuntimeException;
import com.dev.gdstoreservice.models.entity.GdBook;
import com.dev.gdstoreservice.repositories.GdBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(
        readOnly = true,
        propagation = Propagation.REQUIRED
)
public class GdBookServiceGrpc {

    private final GdBookRepository repository;

    public void getAllBooks() {
        try {
            List<GdBook> books = repository.findAll();
            //TODO завести grpc-api и отправить по нему книги
            //TODO сделать блок catch для обработки grpc-ошибок
        } catch (Exception e) {
            log.error("Ошибка в работе метода getAllBooks: {}", e.getMessage());
            throw new GdRuntimeException("gdStoreService.services.grpc", "Ошибка в работе метода getAllBooks");
        }
    }

    public void getBooksByTopic(String topic) {
        if (StringUtils.isBlank(topic)) {
            throw new GdRuntimeException("gdStoreService.services.grpc", "Не передан топик");
        }

        try {
            List<GdBook> books = repository.getAllByTopic(topic);
            //TODO завести grpc-api и отправить по нему книги
            //TODO сделать блок catch для обработки grpc-ошибок
        } catch (Exception e) {
            log.error("Ошибка в работе метода getAllByTopic: {}", e.getMessage());
            throw new GdRuntimeException("gdStoreService.services.grpc", "Ошибка в работе метода getAllByTopic");
        }
    }
}
