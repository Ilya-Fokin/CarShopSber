package com.example.CarShopSber.Validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Класс, представляющий информацию об ошибке валидации для конкретного поля.
 */
@Getter
public class Violation {
    /**
     * Имя поля, в котором произошла ошибка валидации.
     */
    private final String fieldName;
    /**
     * Сообщение об ошибке валидации для данного поля.
     */
    private final String message;
    /**
     * Конструктор класса.
     *
     * @param fieldName Имя поля, в котором произошла ошибка валидации.
     * @param message   Сообщение об ошибке валидации для данного поля.
     */
    public Violation(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}