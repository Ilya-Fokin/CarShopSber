package com.example.CarShopSber.Service.CarService;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.CarImage;
import com.example.CarShopSber.Entities.User;
import com.example.CarShopSber.Repository.CarRepo;
import com.example.CarShopSber.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Реализация сервиса для работы с данными об автомобилях.
 */
@Service
public class CarServiceImpl implements CarService {
    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private UserRepo userRepo;

    /**
     * Сохраняет информацию о новом автомобиле.
     *
     * @param car  Информация о сохраняемом автомобиле.
     * @param user Пользователь, добавляющий автомобиль.
     * @return Сохраненный автомобиль.
     */
    @Override
    @Transactional
    public Car save(Car car, User user) {
        List<CarImage> carImageList = car.getCarImages();
        for (CarImage carImage : carImageList) {
            carImage.setCar(car);
        }
        car.setUser(user);
        car.setCarImages(carImageList);
        logger.info("Сохранение авто {}", car);
        return carRepo.save(car);
    }

    /**
     * Находит все автомобили, принадлежащие конкретному пользователю.
     *
     * @param user Пользователь, чьи автомобили ищутся.
     * @return Список автомобилей пользователя.
     */
    @Override
    public List<Car> findAllByUser(User user) {
        logger.info("Запрос на вывод всех автомобилей пользователем {}", user);
        return carRepo.findByUser(user);
    }

    /**
     * Находит автомобиль по его идентификатору.
     *
     * @param id Идентификатор автомобиля.
     * @return Найденный автомобиль или null, если автомобиль не найден.
     */
    @Override
    public Car findById(UUID id) {
        logger.info("Запрос на поиск авто по id {}", id);
        return carRepo.findById(id).orElse(null);
    }

    /**
     * Проверяет, является ли пользователь владельцем конкретного автомобиля.
     *
     * @param idCar Идентификатор автомобиля.
     * @param user  Пользователь, для которого проверяется владение автомобилем.
     * @return true, если пользователь является владельцем, в противном случае - false.
     */
    @Override
    public boolean isOwner(UUID idCar, User user) {
        Car car = findById(idCar);
        if (car == null) {
            logger.warn("Переданный авто c id {} не найден в бд", idCar);
            return false;
        }
        return car.getUser().equals(user);
    }

    /**
     * Удаляет автомобиль из базы данных и из списка автомобилей пользователя.
     *
     * @param user   Пользователь, у которого удаляется автомобиль.
     * @param carId  Идентификатор удаляемого автомобиля.
     */
    public void deleteCar(User user, UUID carId) {
        logger.info("Попытка удаления авто с id {} пользователем {}", carId, user.getUsername());
        Optional<Car> optionalCar = user.getCars().stream()
                .filter(car -> car.getId().equals(carId))
                .findFirst();

        optionalCar.ifPresent(car -> {
            user.getCars().remove(car);
            carRepo.delete(car);
            userRepo.save(user);
        });
    }

    /**
     * Находит все автомобили по части их названия.
     *
     * @param title Часть названия автомобиля.
     * @return Список автомобилей, чьи названия содержат указанную часть.
     */
    @Override
    public List<Car> findAllByTitle(String title) {
        logger.info("Поиск авто по заголовку {}", title);
        return carRepo.findAllByTitleContaining(title);
    }

    /**
     * Находит все автомобили в системе.
     *
     * @return Список всех автомобилей.
     */
    @Override
    public List<Car> findAll() {
        return carRepo.findAll();
    }
}
