package com.example.CarShopSber.Service.CarImageService;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.CarImage;
import com.example.CarShopSber.Repository.CarImageRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarImageServiceImpl implements CarImageService{

    @Autowired
    private CarImageRepo carImageRepo;

    @Override
    public List<CarImage> findAllByCar(Car car) {
        return carImageRepo.findAllByCar(car);
    }

    @Transactional
    @Override
    public Iterable<CarImage> saveAll(List<CarImage> carImages) {
        return carImageRepo.saveAll(carImages);
    }


}
