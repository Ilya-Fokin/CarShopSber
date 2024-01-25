package com.example.CarShopSber.Validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Violation {
    private final String fieldName;
    private final String message;

    public Violation(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}