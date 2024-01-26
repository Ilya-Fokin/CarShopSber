package com.example.CarShopSber.Service.CarService;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.CarImage;
import com.example.CarShopSber.Entities.User;
import com.example.CarShopSber.Repository.CarRepo;
import com.example.CarShopSber.Service.CarImageService.CarImageServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepo carRepo;

    @Autowired
    private CarImageServiceImpl carImageService;

    @Override
    @Transactional
    public Car save(Car car, User user) {
        List<CarImage> carImageList = car.getCarImages();
        for (CarImage carImage : carImageList) {
            carImage.setCar(car);
        }
        car.setUser(user);
        car.setCarImages(carImageList);
        return carRepo.save(car);
    }

    @Override
    public List<Car> findAllByUser(User user) {
        return carRepo.findByUser(user);
    }
}
