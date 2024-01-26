package com.example.CarShopSber.Entities;

import com.example.CarShopSber.Deserializers.TransmissionDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

/**
 * Перечисление, представляющее виды трансмиссии автомобиля.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = TransmissionDeserializer.class)
public enum Transmission {
    /**
     * Коробка автомат
     */
    AUTOMATIC("Автомат"),
    /**
     * Коробка робот
     */
    ROBOT("Робот"),
    /**
     * Коробка вариатор
     */
    VARIATOR("Вариатор"),
    /**
     * Коробка механика
     */
    MECHANICAL("Механическая");

    /**
     * Строковое представление enum
     */
    private final String transmission;

    /**
     * Конструктор перечисления Transmission.
     *
     * @param transmission Наименование типа трансмиссии.
     */
    Transmission(String transmission) {
        this.transmission = transmission;
    }
}
