package com.example.CarShopSber.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/sign_in")
    public String signIn() {
        return "login.html";
    }

    @GetMapping("/login_error")
    public String loginError() { return "login_error.html"; }

    @GetMapping("/sign_up")
    public String signUp() {
        return "registration.html";
    }

    @GetMapping("/profile")
    public String profile() { return "user_profile.html"; }

    @GetMapping("/add_car")
    public String addCar() { return "add_car_form.html"; }

}
