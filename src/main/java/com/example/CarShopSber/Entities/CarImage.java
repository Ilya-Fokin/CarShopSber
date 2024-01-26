package com.example.CarShopSber.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

/**
 * Сущность, представляющая изображение автомобиля.
 */
@Entity
@Table(name = "car_images")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CarImage {
    /**
     * Уникальный идентификатор изображения автомобиля.
     */
    @Id
    @UuidGenerator
    @Column(name = "id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id = UUID.randomUUID();

    /**
     * Путь к изображению автомобиля.
     */
    @Column(name = "path")
    @NotNull
    private String path;

    /**
     * Автомобиль, к которому привязано изображение.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @JsonBackReference
    private Car car;

    public CarImage(String path) {
        this.path = path;
    }
}
