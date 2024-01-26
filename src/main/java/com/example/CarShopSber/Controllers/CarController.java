package com.example.CarShopSber.Controllers;

import com.example.CarShopSber.Entities.*;
import com.example.CarShopSber.Service.CarService.CarServiceImpl;
import com.example.CarShopSber.Service.UserService.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Класс типа @RestController, обрабатывающий запросы взаимодействия с Car
 *
 * @author Ilya
 * @version 1.0
 */
@RestController
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private UserServiceImpl userService;

    /**
     * Добавляет новый автомобиль.
     *
     * @param car Новый автомобиль для добавления.
     * @return HTTP-ответ с результатом операции.
     */
    @PostMapping("/car/add_car")
    public ResponseEntity<String> addCar(@Valid @RequestBody Car car) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Car car1 = carService.save(car, userService.findByUsername(auth.getName()));
        if (car1 == null) {
            return new ResponseEntity<>("{\"message\":\"Возникла ошибка при добавлении авто\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Возвращает все доступные типы автомобилей.
     *
     * @return Массив типов автомобилей.
     */
    @GetMapping("/car/add_car/get_types")
    public CarType[] getCarTypes() {
        return CarType.values();
    }

    /**
     * Возвращает все доступные типы трансмиссий автомобилей.
     *
     * @return Массив типов трансмиссий.
     */
    @GetMapping("/car/add_car/get_transmissions")
    public Transmission[] getCarTransmissions() {
        return Transmission.values();
    }

    /**
     * Возвращает все доступные приводы автомобилей.
     *
     * @return Массив доступных приводов.
     */
    @GetMapping("/car/add_car/get_wheel_drive")
    public CarWheelDrive[] getCarCarWheelDrive() {
        return CarWheelDrive.values();
    }

    /**
     * Находит автомобиль по его идентификатору.
     *
     * @param id    Идентификатор автомобиля.
     * @return      Найденный автомобиль.
     */
    @GetMapping("/car/{id}/find")
    public Car findCarById(@PathVariable(name = "id") String id) {
        return carService.findById(UUID.fromString(id));
    }

    /**
     * Проверяет, является ли текущий пользователь владельцем автомобиля.
     *
     * @param id    Идентификатор автомобиля.
     * @return      Результат проверки владения автомобилем.
     */
    @GetMapping("/car/{id}/root")
    public boolean carOwner(@PathVariable(name = "id") String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        return carService.isOwner(UUID.fromString(id), user);
    }

    /**
     * Возвращает список автомобилей по части их названия (заголовка).
     *
     * @param title    Часть названия автомобиля.
     * @return         Список найденных автомобилей.
     */
    @GetMapping("/get_cars_by_title")
    public List<Car> getCarsByTitle(@RequestParam String title) {
        System.out.println(title);
        return carService.findAllByTitle(title);
    }

    /**
     * Возвращает список всех автомобилей.
     *
     * @return Список всех автомобилей.
     */
    @GetMapping("/get_all_cars")
    public List<Car> getAllCard() {
        return carService.findAll();
    }

    /**
     * Удаляет автомобиль.
     *
     * @param id    Идентификатор удаляемого автомобиля.
     * @return      HTTP-ответ с результатом операции.
     */
    @PostMapping("/car/drop")
    public ResponseEntity<String> removeCar(@RequestParam String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if (user == null) {
            return new ResponseEntity<>("{\"message\":\"Ошибка: сессия не найдена, необходима авторизация\"}", HttpStatus.BAD_REQUEST);
        } else {
            carService.deleteCar(user, UUID.fromString(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
