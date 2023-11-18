package com.dev.gdstoreservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Топики книг
 */
@Getter
@AllArgsConstructor
public enum Topic {

    JAVA("Java"),

    SPRING("Spring"),

    SQL("SQL");

    private final String topic;
}
