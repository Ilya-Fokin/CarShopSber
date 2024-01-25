package com.example.CarShopSber.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "car")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Car {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id = UUID.randomUUID();

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "brand")
    @NotNull
    private String brand;

    @Column(name = "model")
    @NotNull
    private String model;

    @Column(name = "year_of_release")
    @NotNull
    private Year yearOfRelease;

    @Column(name = "color")
    @NotNull
    private String color;

    @Column(name = "wheel_drive")
    @Enumerated(EnumType.STRING)
    @NotNull
    private CarWheelDrive wheelDrive;

    @Column(name = "mileage")
    @NotNull
    private int mileage;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private CarType type;

    @Column(name = "price")
    @NotNull
    private int price;

    @Column(name = "transmission")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private User user;

    @OneToMany(mappedBy = "car")
    private List<CarImage> carImages = new ArrayList<>();

    public Car(String title, String description, String brand, String model, Year yearOfRelease,
               String color, CarWheelDrive wheelDrive, int mileage, CarType type, int price,
               Transmission transmission, User user) {
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.model = model;
        this.yearOfRelease = yearOfRelease;
        this.color = color;
        this.wheelDrive = wheelDrive;
        this.mileage = mileage;
        this.type = type;
        this.price = price;
        this.transmission = transmission;
    }
}
