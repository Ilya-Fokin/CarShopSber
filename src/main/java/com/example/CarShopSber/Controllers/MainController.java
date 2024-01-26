package com.example.CarShopSber.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер, отвечающий за обработку запросов, связанных с основными страницами приложения.
 */
@Controller
public class MainController {

    /**
     * Обработка запроса для отображения главной страницы.
     *
     * @return Название HTML-шаблона главной страницы.
     */
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    /**
     * Обработка запроса для отображения страницы входа в систему.
     *
     * @return Название HTML-шаблона страницы входа в систему.
     */
    @GetMapping("/sign_in")
    public String signIn() {
        return "login.html";
    }

    /**
     * Обработка запроса для отображения страницы с сообщением об ошибке входа.
     *
     * @return Название HTML-шаблона страницы с сообщением об ошибке входа.
     */
    @GetMapping("/login_error")
    public String loginError() { return "login_error.html"; }

    /**
     * Обработка запроса для отображения страницы регистрации пользователя.
     *
     * @return Название HTML-шаблона страницы регистрации пользователя.
     */
    @GetMapping("/sign_up")
    public String signUp() {
        return "registration.html";
    }

    /**
     * Обработка запроса для отображения страницы профиля пользователя.
     *
     * @return Название HTML-шаблона страницы профиля пользователя.
     */
    @GetMapping("/profile")
    public String profile() { return "user_profile.html"; }

    /**
     * Обработка запроса для отображения страницы добавления автомобиля.
     *
     * @return Название HTML-шаблона страницы добавления автомобиля.
     */
    @GetMapping("/add_car")
    public String addCar() { return "add_car_form.html"; }

    /**
     * Обработка запроса для отображения страницы избранных автомобилей.
     *
     * @return Название HTML-шаблона страницы избранных автомобилей.
     */
    @GetMapping("/favourite")
    public String favourite() {
        return "favourite.html";
    }

}
