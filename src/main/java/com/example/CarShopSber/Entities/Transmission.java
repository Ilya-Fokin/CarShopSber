package com.example.CarShopSber.Entities;

public enum Transmission {
    AUTOMATIC("Автомат"),
    ROBOT("Робот"),
    VARIATOR("Вариатор"),
    MECHANICAL("Механическая");

    private final String transmission;

    Transmission(String transmission) {
        this.transmission = transmission;
    }

    public String getTransmission() {
        return transmission;
    }
}
