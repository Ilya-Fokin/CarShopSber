package com.example.CarShopSber.Controllers;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.User;
import com.example.CarShopSber.Service.CarService.CarServiceImpl;
import com.example.CarShopSber.Service.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/get_current_user")
    @ResponseBody
    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        System.out.println(user.toString());
        return user;
    }

    @GetMapping("/get_all_car_by_user")
    public List<Car> getUserCar() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        List<Car> cars = carService.findAllByUser(user);
        System.out.println(cars);
        return cars;
    }
}
