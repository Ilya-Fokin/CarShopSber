package com.example.CarShopSber.Service.CarService;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.User;

import java.util.List;

public interface CarService {
    Car save(Car car, User user);
    List<Car> findAllByUser(User user);
}
