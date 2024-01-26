package com.example.CarShopSber.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
 * Сущность, представляющая роль пользователя.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    /**
     * Уникальный идентификатор роли.
     */
    @Id
    @UuidGenerator
    @Column(name = "id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id = UUID.randomUUID();

    /**
     * Наименование роли.
     */
    @NotNull
    private String name;

    /**
     * Конструктор сущности Role.
     *
     * @param name Наименование роли.
     */
    public Role(String name) {
        this.name = name;
    }
}
