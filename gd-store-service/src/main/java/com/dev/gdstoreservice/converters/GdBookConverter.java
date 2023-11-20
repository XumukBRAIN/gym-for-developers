package com.dev.gdstoreservice.converters;

import com.dev.gdstoreservice.exceptions.GdRuntimeException;
import com.dev.gdstoreservice.models.dto.GdBookDto;
import com.dev.gdstoreservice.models.entity.GdBook;
import com.dev.grpc.gdstore.book.GdBookService;

import java.math.BigDecimal;

/**
 * Конвертер GdBook <-> GdBookDto
 */
public class GdBookConverter {

    public static GdBookDto toDto(GdBook entity) {
        if (entity == null) {
            throw new GdRuntimeException("gdStoreService.converters.toDto", "Параметр равен null");
        }

        return GdBookDto.builder()
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .price(entity.getPrice())
                .pages(entity.getPages())
                .creationDate(entity.getCreationDate())
                .topic(entity.getTopic())
                .build();
    }

    public static GdBook toEntity(GdBookDto dto) {
        if (dto == null) {
            throw new GdRuntimeException("gdStoreService.converters.toEntity", "Параметр равен null");
        }

        return GdBook.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .price(dto.getPrice())
                .pages(dto.getPages())
                .creationDate(dto.getCreationDate())
                .topic(dto.getTopic())
                .build();
    }

    public static GdBookService.Book toGrpcObject(GdBook entity) {
        if (entity == null) {
            throw new GdRuntimeException("gdStoreService.converters.toGrpcObject", "Параметр равен null");
        }

        GdBookService.Book.Builder builder = GdBookService.Book.newBuilder()
                .setTitle(entity.getTitle())
                .setAuthor(entity.getAuthor())
                .setTopic(entity.getTopic())
                .setPages(entity.getPages())
                .setCreationDate(String.valueOf(entity.getCreationDate()));

        BigDecimal price = entity.getPrice();
        if (price == null) {
            builder.setPrice(0.0);
        } else {
            builder.setPrice(price.doubleValue());
        }

        return builder.build();
    }
}
