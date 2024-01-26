package com.example.CarShopSber.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сущность, представляющая избранные пользователя
 */
@Entity
@Table(name = "favourite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favourite {
    /**
     * Уникальный идентификатор избранного.
     */
    @Id
    @UuidGenerator
    @Column(name = "id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private final UUID id = UUID.randomUUID();

    /**
     * Пользователь, которому принадлежат избранные
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private User userFavourite;

    /**
     * Список избранных автомобилей.
     */
    @ManyToMany
    @JoinTable(
            name = "favourite_cars",
            joinColumns = @JoinColumn(name = "favourite_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    @JsonManagedReference
    private List<Car> favoriteCars = new ArrayList<>();
}
