package com.dev.gdmainservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuestionSections {
    HIBERNATE(1, "Hibernate section", "Основные вопросы про Hibernate"),
    SPRING(2, "Spring section", "Вопросы и нюансы про Spring в целом"),
    JAVA(3, "Java section", "Тонкости языка программирования Java"),
    SQL(4, "SQL section", "Раздел вопросов про SQL"),
    PATTERNS(5, "Patterns section", "Раздел вопросов про Patterns");

    private final Integer code;
    private final String title;
    private final String description;
}
