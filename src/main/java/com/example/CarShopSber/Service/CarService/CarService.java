package com.example.CarShopSber.Service.CarService;

import com.example.CarShopSber.Dto.CarDto;
import com.example.CarShopSber.Entities.Car;

import java.util.List;

public interface CarService {
    Car save(CarDto carDto);
}
