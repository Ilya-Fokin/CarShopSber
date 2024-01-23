package com.example.CarShopSber.Entities;

public enum RoleEnum {
    USER("user"), ADMIN("admin");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }
}
