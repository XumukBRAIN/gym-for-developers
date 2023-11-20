package com.dev.gdstoreservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

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

    public static boolean contains(String topic) {
        return Arrays.stream(Topic.values())
                .map(Topic::getTopic)
                .anyMatch(x -> x.equals(topic));
    }
}
