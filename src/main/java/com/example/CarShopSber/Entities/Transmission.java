package com.example.CarShopSber.Entities;

import com.example.CarShopSber.Deserializers.TransmissionDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = TransmissionDeserializer.class)
public enum Transmission {
    AUTOMATIC("Автомат"),
    ROBOT("Робот"),
    VARIATOR("Вариатор"),
    MECHANICAL("Механическая");

    private final String transmission;

    Transmission(String transmission) {
        this.transmission = transmission;
    }

}
