package com.dev.gdmainservice.converters;

import com.dev.gdmainservice.configs.HttpProperties;
import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.dto.GdBookDto;
import com.dev.grpc.gdstore.book.GdBookService;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.time.LocalDate;

/**
 * Конвертер GdBook <-> GdBookDto
 */
public class GdBookConverter {

    public static GdBookDto toDto(GdBookService.Book book) {
        if (book == null) {
            throw new GdRuntimeException("Параметр равен null");
        }

        return GdBookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .topic(book.getTopic())
                .pages(book.getPages())
                .creationDate(LocalDate.parse(book.getCreationDate()))
                .price(BigDecimal.valueOf(book.getPrice()))
                .build();
    }

    public HttpRequest.Builder buildHttpRequest(HttpRequest.BodyPublisher body, HttpProperties properties) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        String method = properties.getHttpMethod();
        if (StringUtils.hasText(method) || method.equals("POST")) {
            builder.POST(body);
        } else {
            builder.method(method, body);
        }

        if (body.contentLength() != 0) {
            builder.setHeader(HttpHeaders.CONTENT_TYPE, properties.getMediaType().toString());
        }

        builder.timeout(properties.getSettings().getCallTimeout());
        return builder;
    }
}
