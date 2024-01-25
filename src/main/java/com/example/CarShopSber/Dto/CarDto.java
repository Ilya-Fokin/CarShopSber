package com.example.CarShopSber.Dto;

import com.example.CarShopSber.Entities.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDto {
    private String title;

    private String description;

    private String brand;

    private String model;

    private Year yearOfRelease;

    private String color;

    private String wheelDrive;

    private int mileage;

    private String type;

    private int price;

    private String transmission;

    private List<String> carImages = new ArrayList<>();
}
