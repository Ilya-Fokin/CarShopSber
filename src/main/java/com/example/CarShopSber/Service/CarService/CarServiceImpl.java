package com.example.CarShopSber.Service.CarService;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.CarImage;
import com.example.CarShopSber.Entities.User;
import com.example.CarShopSber.Repository.CarRepo;
import com.example.CarShopSber.Repository.UserRepo;
import com.example.CarShopSber.Service.CarImageService.CarImageServiceImpl;
import com.example.CarShopSber.Service.UserService.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepo carRepo;

    @Autowired
    private UserRepo userRepo;

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

    @Override
    public Car findById(UUID id) {
        return carRepo.findById(id).orElse(null);
    }

    @Override
    public boolean isOwner(UUID idCar, User user) {
        Car car = findById(idCar);
        if (car == null) {
            return false;
        }
        return car.getUser().equals(user);
    }

    public void deleteCar(User user, UUID carId) {
        Optional<Car> optionalCar = user.getCars().stream()
                .filter(car -> car.getId().equals(carId))
                .findFirst();

        optionalCar.ifPresent(car -> {
            user.getCars().remove(car);
            carRepo.delete(car);
            userRepo.save(user);
        });
    }

    @Override
    public List<Car> findAllByTitle(String title) {
        return carRepo.findAllByTitleContaining(title);
    }

    @Override
    public List<Car> findAll() {
        return carRepo.findAll();
    }
}
