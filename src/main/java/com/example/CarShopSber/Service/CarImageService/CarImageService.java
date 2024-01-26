package com.example.CarShopSber.Service.CarImageService;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.CarImage;

import java.util.List;

public interface CarImageService {
    List<CarImage> findAllByCar(Car car);
    Iterable<CarImage> saveAll(List<CarImage> carImages);
}
