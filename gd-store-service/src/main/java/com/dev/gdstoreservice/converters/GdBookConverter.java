package com.dev.gdstoreservice.converters;

import com.dev.gdstoreservice.exceptions.GdRuntimeException;
import com.dev.gdstoreservice.models.dto.GdBookDto;
import com.dev.gdstoreservice.models.entity.GdBook;

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
}
