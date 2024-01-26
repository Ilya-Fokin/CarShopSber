package com.example.CarShopSber.Service.FavouriteService;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.Favourite;
import com.example.CarShopSber.Entities.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Интерфейс сервиса для работы с избранными автомобилями пользователя.
 */
public interface FavouriteService {
    /**
     * Добавляет автомобиль в список избранных пользователя.
     *
     * @param user Пользователь, добавляющий автомобиль в избранное.
     * @param car  Автомобиль, который добавляется в избранное.
     * @return Объект Favourite после добавления автомобиля.
     */
    Favourite addToFavorites(User user, Car car);
    /**
     * Возвращает список избранных автомобилей пользователя.
     *
     * @param user Пользователь, чей список избранных автомобилей запрашивается.
     * @return Список избранных автомобилей пользователя.
     */
    List<Car> getFavoriteCars(User user);
    /**
     * Удаляет автомобиль из списка избранных пользователя.
     *
     * @param user  Пользователь, у которого удаляется автомобиль из избранного.
     * @param carId Идентификатор удаляемого автомобиля.
     */
    void removeCarFromFavorites(User user, UUID carId);
}
