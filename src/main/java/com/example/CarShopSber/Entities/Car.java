package com.example.CarShopSber.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сущность, представляющая автомобиль.
 */
@Entity
@Table(name = "car")
@Getter
@Setter
@NoArgsConstructor
public class Car {
    /**
     * Уникальный идентификатор автомобиля.
     */
    @Id
    @UuidGenerator
    @Column(name = "id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private final UUID id = UUID.randomUUID();

    /**
     * Заголовок автомобиля.
     */
    @Column(name = "title")
    @NotNull(message = "Поле 'Заголовок' не может быть пустым")
    private String title;

    /**
     * Описание автомобиля.
     */
    @Column(name = "description")
    @NotNull(message = "Поле 'Описание' не может быть пустым")
    private String description;

    /**
     * Марка автомобиля.
     */
    @Column(name = "brand")
    @NotNull(message = "Поле 'Марка' не может быть пустым")
    private String brand;

    /**
     * Модель автомобиля.
     */
    @Column(name = "model")
    @NotNull(message = "Поле 'Модель' не может быть пустым")
    private String model;

    /**
     * Год выпуска автомобиля.
     */
    @Column(name = "year_of_release")
    @NotNull
    @Pattern(regexp = "^\\d{4}$", message = "Поле 'Год выпуска' должны быть формата - 2022")
    private String yearOfRelease;

    /**
     * Цвет автомобиля.
     */
    @Column(name = "color")
    @NotNull(message = "Поле 'Цвет' не может быть пустым")
    private String color;

    /**
     * Привод автомобиля.
     */
    @Column(name = "wheel_drive")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Поле 'Привод' не может быть пустым")
    private CarWheelDrive wheelDrive;

    /**
     * Пробег автомобиля.
     */
    @Column(name = "mileage")
    @NotNull(message = "Поле 'Пробег' не может быть пустым")
    @Min(value = 1, message = "Минимальный размер пробега - 1")
    private int mileage;

    /**
     * Тип кузова автомобиля.
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Поле 'Кузов' не может быть пустым")
    private CarType type;

    /**
     * Цена автомобиля.
     */
    @Column(name = "price")
    @NotNull(message = "Поле 'Цена' не может быть пустым")
    @Min(value = 1, message = "Минимальная цена - 1")
    private int price;

    /**
     * Трансмиссия автомобиля.
     */
    @Column(name = "transmission")
    @NotNull(message = "Поле 'Трансмиссия' не может быть пустым")
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    /**
     * Владелец автомобиля.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    /**
     * Список изображений автомобиля.
     */
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CarImage> carImages = new ArrayList<>();

    /**
     * Конструктор для создания экземпляра автомобиля с определенными значениями полей.
     *
     * @param title         Заголовок автомобиля.
     * @param description   Описание автомобиля.
     * @param brand         Марка автомобиля.
     * @param model         Модель автомобиля.
     * @param yearOfRelease Год выпуска автомобиля.
     * @param color         Цвет автомобиля.
     * @param wheelDrive    Тип привода автомобиля.
     * @param mileage       Пробег автомобиля.
     * @param type          Тип кузова автомобиля.
     * @param price         Цена автомобиля.
     * @param transmission  Трансмиссия автомобиля.
     * @param user          Владелец автомобиля.
     */
    public Car(String title, String description, String brand, String model, String yearOfRelease,
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

    @Override
    public String toString() {
        return "Car{" +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", color='" + color + '\'' +
                ", wheelDrive=" + wheelDrive +
                ", mileage=" + mileage +
                ", type=" + type +
                ", price=" + price +
                ", transmission=" + transmission +
                ", user=" + user +
                ", carImages=" + carImages +
                '}';
    }
}
