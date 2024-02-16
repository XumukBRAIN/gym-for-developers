package com.dev.gdmainservice.services;

import com.dev.gdmainservice.configs.HttpProperties;
import com.dev.gdmainservice.converters.GdBookConverter;
import com.dev.gdmainservice.models.dto.GdBookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class GdBookServiceHttp {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final GdBookConverter converter;
    private final HttpProperties properties;

    /**
     * Метод сохранения новой книги
     * HTTP-запрос в микросервис gd-store-service
     *
     *
     * @param book Данные новой книги
     * @return Тело HTTP-ответа
     */
    public String save(GdBookDto book) {
        try {
            String stringObject = objectMapper.writeValueAsString(book);
            HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(stringObject);
            HttpRequest.Builder request = converter.buildHttpRequest(body, properties);
            HttpResponse<String> response = httpClient.send(request.build(), HttpResponse.BodyHandlers.ofString());
            log.info("Получен ответ от сервиса-магазина, тело ответа: {}", response);
            return response.body();
        } catch (IOException | InterruptedException e) {
            log.error("Ошибка в ходе сохранения новой книги", e);
            throw new RuntimeException(e);
        }
    }
}
