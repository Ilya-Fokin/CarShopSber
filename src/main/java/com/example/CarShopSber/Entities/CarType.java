package com.example.CarShopSber.Entities;

public enum CarType {
    SEDAN("Седан"),
    OFF_ROAD("Внедорожник"),
    WAGON("Универсал");

    private final String type;

    CarType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
