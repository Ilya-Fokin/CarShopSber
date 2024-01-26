package com.example.CarShopSber.Repository;

import com.example.CarShopSber.Entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для доступа к данным о ролях пользователей в базе данных.
 */
public interface RoleRepo extends CrudRepository<Role, UUID> {
    /**
     * Находит роль по её имени.
     *
     * @param name Имя роли.
     * @return Объект Optional, содержащий роль или пустой, если роль не найдена.
     */
    Optional<Role> findByName(String name);
}
