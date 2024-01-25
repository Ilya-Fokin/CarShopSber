package com.example.CarShopSber.Repository;

import com.example.CarShopSber.Entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepo extends CrudRepository<Role, UUID> {
    Optional<Role> findByName(String name);
}
