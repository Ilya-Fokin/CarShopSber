package com.example.CarShopSber.Repository;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.CarImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarImageRepo extends CrudRepository<CarImage, UUID> {
    List<CarImage> findAllByCar(Car car);
    CarImage save(CarImage carImage);
}
