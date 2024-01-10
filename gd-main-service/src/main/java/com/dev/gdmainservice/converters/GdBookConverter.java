package com.dev.gdmainservice.converters;

import com.dev.gdmainservice.exceptions.GdRuntimeException;
import com.dev.gdmainservice.models.dto.GdBookDto;
import com.dev.grpc.gdstore.book.GdBookService;

import java.math.BigDecimal;
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
}
