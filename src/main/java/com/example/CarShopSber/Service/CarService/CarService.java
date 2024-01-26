package com.example.CarShopSber.Service.CarService;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.User;

import java.util.List;
import java.util.UUID;

public interface CarService {
    Car save(Car car, User user);
    List<Car> findAllByUser(User user);
    Car findById(UUID id);
    boolean isOwner(UUID idCar, User user);
    List<Car> findAllByTitle(String title);
    List<Car> findAll();
}
