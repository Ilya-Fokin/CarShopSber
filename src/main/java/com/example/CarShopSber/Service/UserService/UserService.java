package com.example.CarShopSber.Service.UserService;

import com.example.CarShopSber.Entities.User;

import java.util.UUID;

/**
 * Сервис для работы с пользователями.
 */
public interface UserService {
    /**
     * Сохраняет пользователя в системе.
     *
     * @param user Пользователь для сохранения.
     * @return Сохраненный пользователь.
     */
    User save(User user);
    /**
     * Находит пользователя по его имени пользователя.
     *
     * @param username Имя пользователя.
     * @return Найденный пользователь или null, если пользователь не найден.
     */
    User findByUsername(String username);
    /**
     * Находит пользователя по его адресу электронной почты.
     *
     * @param mail Адрес электронной почты пользователя.
     * @return Найденный пользователь или null, если пользователь не найден.
     */
    User findByMail(String mail);
}
