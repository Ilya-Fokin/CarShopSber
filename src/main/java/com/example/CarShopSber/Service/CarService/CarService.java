package com.example.CarShopSber.Service.CarService;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.User;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с данными об автомобилях.
 */
public interface CarService {

    /**
     * Сохраняет информацию о новом автомобиле.
     *
     * @param car  Информация о сохраняемом автомобиле.
     * @param user Пользователь, добавляющий автомобиль.
     * @return Сохраненный автомобиль.
     */
    Car save(Car car, User user);
    /**
     * Находит все автомобили, принадлежащие конкретному пользователю.
     *
     * @param user Пользователь, чьи автомобили ищутся.
     * @return Список автомобилей пользователя.
     */
    List<Car> findAllByUser(User user);
    /**
     * Находит автомобиль по его идентификатору.
     *
     * @param id Идентификатор автомобиля.
     * @return Найденный автомобиль или null, если автомобиль не найден.
     */
    Car findById(UUID id);
    /**
     * Проверяет, является ли пользователь владельцем конкретного автомобиля.
     *
     * @param idCar Идентификатор автомобиля.
     * @param user  Пользователь, для которого проверяется владение автомобилем.
     * @return true, если пользователь является владельцем, в противном случае - false.
     */
    boolean isOwner(UUID idCar, User user);
    /**
     * Находит все автомобили по части их названия.
     *
     * @param title Часть названия автомобиля.
     * @return Список автомобилей, чьи названия содержат указанную часть.
     */
    List<Car> findAllByTitle(String title);
    /**
     * Находит все автомобили в бд.
     *
     * @return Список всех автомобилей.
     */
    List<Car> findAll();
}
