package com.example.CarShopSber.Service.RoleService;

import com.example.CarShopSber.Entities.Role;
import com.example.CarShopSber.Repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Реализация сервиса для работы с ролями пользователей.
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepo roleRepo;

    /**
     * Находит роль по её имени.
     *
     * @param name Имя роли, которую необходимо найти.
     * @return Роль с указанным именем или null, если роль не найдена.
     */

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name).orElse(null);
    }
}
