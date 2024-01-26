package com.example.CarShopSber.Controllers;

import com.example.CarShopSber.Entities.*;
import com.example.CarShopSber.Service.CarService.CarServiceImpl;
import com.example.CarShopSber.Service.UserService.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/add_car")
public class CarController {
    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("")
    public ResponseEntity<String> addCar(@Valid @RequestBody Car car) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Car car1 = carService.save(car, userService.findByUsername(auth.getName()));
        if (car1 == null) {
            return new ResponseEntity<>("{\"message\":\"Возникла ошибка при добавлении авто\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get_types")
    public CarType[] getCarTypes() {
        return CarType.values();
    }

    @GetMapping("/get_transmissions")
    public Transmission[] getCarTransmissions() {
        return Transmission.values();
    }

    @GetMapping("/get_wheel_drive")
    public CarWheelDrive[] getCarCarWheelDrive() {
        return CarWheelDrive.values();
    }
}
