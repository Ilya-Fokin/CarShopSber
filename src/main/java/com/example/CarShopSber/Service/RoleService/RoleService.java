package com.example.CarShopSber.Service.RoleService;

import com.example.CarShopSber.Entities.Role;

import java.util.Optional;

/**
 * Сервис для работы с ролями пользователей.
 */
public interface RoleService {
    /**
     * Находит роль по её имени.
     *
     * @param name Имя роли, которую необходимо найти.
     * @return Роль с указанным именем или null, если роль не найдена.
     */
    Role findByName(String name);
}
