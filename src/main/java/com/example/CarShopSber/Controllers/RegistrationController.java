package com.example.CarShopSber.Controllers;

import com.example.CarShopSber.Entities.User;
import com.example.CarShopSber.Service.UserService.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/sign_up")
    public ResponseEntity<String> signUp(@Valid @RequestBody User user) {
        System.out.println(user.toString());
        User user2 = userService.save(user);
        if (user2 == null) {
            return new ResponseEntity<>("{\"message\":\"Пользователь с таким логином или почтой уже существует\"}", HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(HttpStatus.OK);
    }
}
