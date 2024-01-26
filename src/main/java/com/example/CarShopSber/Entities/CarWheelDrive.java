package com.example.CarShopSber.Entities;

import com.example.CarShopSber.Deserializers.CarWheelDriveDeserializer;
import com.example.CarShopSber.Deserializers.TransmissionDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

/**
 * Перечисление, представляющее типы привода автомобиля.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = CarWheelDriveDeserializer.class)
public enum CarWheelDrive {
    /**
     * Передний привод.
     */
    FRONT_WHEEL_DRIVE("Передний привод"),
    /**
     * Полный привод.
     */
    FOUR_WHEEL_DRIVE("Полный привод"),
    /**
     * Задний привод.
     */
    REAR_WHEEL_DRIVE("Задний привод");

    /**
     * Строковое представление Enum
     */
    private final String wheelDrive;

    /**
     * Конструктор enum CarWheelDrive.
     *
     * @param wheelDrive Тип привода автомобиля.
     */
    CarWheelDrive(String wheelDrive) {
        this.wheelDrive = wheelDrive;
    }

}
