package com.example.CarShopSber.Entities;

import com.example.CarShopSber.Deserializers.CarTypeDeserializer;
import com.example.CarShopSber.Deserializers.TransmissionDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = CarTypeDeserializer.class)
public enum CarType {
    SEDAN("Седан"),
    OFF_ROAD("Внедорожник"),
    WAGON("Универсал");

    private final String type;

    CarType(String type) {
        this.type = type;
    }

}
