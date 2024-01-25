package com.example.CarShopSber.Service.CarService;

import com.example.CarShopSber.Dto.CarDto;
import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Repository.CarRepo;
import com.example.CarShopSber.Service.CarService.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepo carRepo;

    @Override
    public Car save(CarDto carDto) {
        return null;
    }
}
