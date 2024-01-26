package com.example.CarShopSber.Validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Класс, представляющий ответ с сообщением об ошибках валидации.
 */
@Getter
public class ValidationErrorResponse {
    /**
     * Список нарушений (ошибок) валидации.
     */
    private final List<Violation> violations;
    /**
     * Конструктор класса.
     *
     * @param violations Список нарушений (ошибок) валидации.
     */
    public ValidationErrorResponse(List<Violation> violations) {
        this.violations = violations;
    }
}
