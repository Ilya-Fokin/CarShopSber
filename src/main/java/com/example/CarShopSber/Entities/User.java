package com.example.CarShopSber.Entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
public class User {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;

    @Column(name = "number_phone")
    private String numberPhone;

    @OneToMany(mappedBy = "user")
    private List<Car> cars = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    public User(String username, String mail, String password, String numberPhone) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.numberPhone = numberPhone;
    }
}
