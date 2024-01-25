package com.example.CarShopSber.Repository;

import com.example.CarShopSber.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;

import java.util.Optional;
import java.util.UUID;


public interface UserRepo extends CrudRepository<User, UUID> {
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
    User findByMail(String mail);
}
