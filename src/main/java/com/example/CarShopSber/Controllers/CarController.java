package com.example.CarShopSber.Controllers;

import com.example.CarShopSber.Dto.CarDto;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @PostMapping("/add_car")
    public RequestEntity<String> addCar(@RequestBody CarDto carDto) {

    }
}
