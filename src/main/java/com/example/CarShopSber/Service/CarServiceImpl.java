package com.example.CarShopSber.Service;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarRepo carRepo;
}
