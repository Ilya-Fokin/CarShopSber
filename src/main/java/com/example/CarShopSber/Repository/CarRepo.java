package com.example.CarShopSber.Repository;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для доступа к данным об автомобилях в базе данных.
 */
public interface CarRepo extends CrudRepository<Car, UUID> {
    /**
     * Возвращает список всех автомобилей в базе данных.
     *
     * @return Список всех автомобилей.
     */
    List<Car> findAll();
    /**
     * Находит автомобиль в базе данных по его идентификатору.
     *
     * @param id Идентификатор автомобиля.
     * @return Объект Optional, содержащий автомобиль или пустой, если автомобиль не найден.
     */
    Optional<Car> findById(UUID id);
    /**
     * Находит список автомобилей, связанных с указанным пользователем.
     *
     * @param user Пользователь, к которому привязаны автомобили.
     * @return Список автомобилей пользователя.
     */
    List<Car> findByUser(User user);
    /**
     * Находит все автомобили, у которых заголовок содержит указанную подстроку.
     *
     * @param title Подстрока, которую необходимо найти в заголовке автомобиля.
     * @return Список автомобилей, у которых заголовок содержит указанную подстроку.
     */
    List<Car> findAllByTitleContaining(String title);
}
