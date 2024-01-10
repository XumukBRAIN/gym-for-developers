package com.dev.gdstoreservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Топики книг
 */
@Getter
@Slf4j
@AllArgsConstructor
public enum Topic {

    JAVA("JAVA"),

    SPRING("SPRING"),

    SQL("SQL");

    private final String topic;

    private static final Set<String> topics =
            Arrays.stream(Topic.values()).map(Topic::getTopic).collect(Collectors.toSet());

    public static boolean contains(String topic) {
        if (StringUtils.isBlank(topic)) {
            log.warn("Не передан топик в enums.Topic.contains(String)");
            return false;
        }

        return topics.contains(topic.toUpperCase());
    }
}
