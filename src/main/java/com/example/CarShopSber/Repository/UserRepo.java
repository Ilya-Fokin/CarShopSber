package com.example.CarShopSber.Repository;

import com.example.CarShopSber.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для доступа к данным о пользователях в базе данных.
 */
public interface UserRepo extends CrudRepository<User, UUID> {
    /**
     * Находит пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Объект Optional, содержащий пользователя или пустой, если пользователь не найден.
     */
    Optional<User> findById(UUID id);
    /**
     * Находит пользователя по его имени пользователя.
     *
     * @param username Имя пользователя.
     * @return Объект Optional, содержащий пользователя или пустой, если пользователь не найден.
     */
    Optional<User> findByUsername(String username);
    /**
     * Находит пользователя по его адресу электронной почты.
     *
     * @param mail Адрес электронной почты пользователя.
     * @return Найденный пользователь или null, если пользователь не найден.
     */
    User findByMail(String mail);
}
