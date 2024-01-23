package com.example.CarShopSber.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "car_images")
@Getter
@Setter
@EqualsAndHashCode
public class CarImage {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;
    @Column(name = "path")
    @NotNull
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Car car;

    public CarImage(String path) {
        this.path = path;
    }
}
