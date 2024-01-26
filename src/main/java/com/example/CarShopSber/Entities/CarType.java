package com.example.CarShopSber.Entities;

import com.example.CarShopSber.Deserializers.CarTypeDeserializer;
import com.example.CarShopSber.Deserializers.TransmissionDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

/**
 * Перечисление, представляющее типы кузова автомобиля.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = CarTypeDeserializer.class)
public enum CarType {
    /**
     * Седан.
     */
    SEDAN("Седан"),
    /**
     * Внедорожник.
     */
    OFF_ROAD("Внедорожник"),
    /**
     * Универсал.
     */
    WAGON("Универсал");
    /**
     * Строковое представление
     */
    private final String type;

    /**
     * Конструктор enum CarType.
     *
     * @param type Тип кузова автомобиля.
     */
    CarType(String type) {
        this.type = type;
    }
}
