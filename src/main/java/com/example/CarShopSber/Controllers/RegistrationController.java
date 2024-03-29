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

/**
 * Контроллер, отвечающий за обработку запросов, связанных с регистрацией пользователей.
 */
@RestController
public class RegistrationController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * Обработка запроса на регистрацию нового пользователя.
     *
     * @param user Запрос на регистрацию пользователя.
     * @return HTTP-ответ с сообщением об успешной регистрации или ошибкой.
     */
    @PostMapping("/sign_up")
    public ResponseEntity<String> signUp(@Valid @RequestBody User user) {
        User user2 = userService.save(user);
        if (user2 == null) {
            return new ResponseEntity<>("{\"message\":\"Пользователь с таким логином или почтой уже существует\"}", HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(HttpStatus.OK);
    }
}
