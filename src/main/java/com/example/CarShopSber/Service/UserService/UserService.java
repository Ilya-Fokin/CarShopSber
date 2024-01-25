package com.example.CarShopSber.Service.UserService;

import com.example.CarShopSber.Entities.User;

import java.util.UUID;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
    User findByMail(String mail);
}
