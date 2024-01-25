package com.example.CarShopSber.Service.RoleService;

import com.example.CarShopSber.Entities.Role;

import java.util.Optional;

public interface RoleService {
    Role findByName(String name);
}
