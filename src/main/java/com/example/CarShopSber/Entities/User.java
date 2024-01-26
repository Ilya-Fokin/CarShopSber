package com.example.CarShopSber.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @UuidGenerator
    @Column(name = "id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id = UUID.randomUUID();

    @Column(name = "username")
    @NotEmpty(message = "Поле 'Логин' не должно быть пустым")
    private String username;

    @Column(name = "first_name")
    @NotEmpty(message = "Поле 'Имя' не должно быть пустым")
    private String firstName;

    @Column(name = "second_name")
    @NotEmpty(message = "Поле 'Фамилия' не должно быть пустым")
    private String secondName;

    @Column(name = "mail")
    @NotEmpty(message = "Поле 'Почта' не должно быть пустым")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "Неверный формат почты")
    private String mail;

    @Column(name = "password")
    @NotEmpty(message = "Поле 'Пароль' не должно быть пустым")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Поле 'Пароль' должно содержать минимум: 1 заглавную букву, 1 строчную букву, 1 цифру, 8 символов")
    private String password;

    @Column(name = "number_phone")
    @NotEmpty(message = "Поле 'Телефон' не должно быть пустым")
    @Pattern(regexp = "^(\\+7|8)[-\\s]?(\\d{3})[-\\s]?(\\d{3})[-\\s]?(\\d{2})[-\\s]?(\\d{2})$",
    message = "Неверный формат номера телефона")
    private String numberPhone;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "userFavourite")
    @JsonIgnore
    private Favourite favourite;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Car> cars = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    public User(String username, String firstName, String secondName, String mail, String password, String numberPhone) {
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.mail = mail;
        this.password = password;
        this.numberPhone = numberPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", roles=" + roles +
                '}';
    }
}
