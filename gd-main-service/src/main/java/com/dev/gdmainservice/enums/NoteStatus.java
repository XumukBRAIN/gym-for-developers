package com.dev.gdmainservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Статус заметки
 */
@Getter
@AllArgsConstructor
public enum NoteStatus {
    IN_REVIEW(1, "Рассмотрение"),
    ACCEPTED(2, "Принята"),
    REJECTED(3, "Отклонена"),
    DELETED(4, "Удалена"),
    RECOVERED(5, "Восстановлена");

    private final Integer code;
    private final String description;
}
