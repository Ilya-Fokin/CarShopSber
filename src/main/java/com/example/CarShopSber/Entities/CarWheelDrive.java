package com.example.CarShopSber.Entities;

import com.example.CarShopSber.Deserializers.CarWheelDriveDeserializer;
import com.example.CarShopSber.Deserializers.TransmissionDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = CarWheelDriveDeserializer.class)
public enum CarWheelDrive {
    FRONT_WHEEL_DRIVE("Передний привод"),
    FOUR_WHEEL_DRIVE("Полный привод"),
    REAR_WHEEL_DRIVE("Задний привод");

    private final String wheelDrive;

    CarWheelDrive(String wheelDrive) {
        this.wheelDrive = wheelDrive;
    }

}
