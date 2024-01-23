package com.example.CarShopSber.Entities;

public enum CarWheelDrive {
    FRONT_WHEEL_DRIVE("Передний привод"),
    FOUR_WHEEL_DRIVE("Полный привод"),
    REAR_WHEEL_DRIVE("Задний привод");

    private final String wheelDrive;

    CarWheelDrive(String wheelDrive) {
        this.wheelDrive = wheelDrive;
    }

    public String getWheelDrive() {
        return wheelDrive;
    }
}
