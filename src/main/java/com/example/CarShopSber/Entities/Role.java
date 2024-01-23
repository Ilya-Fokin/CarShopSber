package com.example.CarShopSber.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;

    @NotNull
    private String role;

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();

    public Role(RoleEnum roleEnum) {
        this.role = roleEnum.name();
    }
}
