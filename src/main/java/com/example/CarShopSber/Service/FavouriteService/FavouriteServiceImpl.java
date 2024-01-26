package com.example.CarShopSber.Service.FavouriteService;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.Favourite;
import com.example.CarShopSber.Entities.User;
import com.example.CarShopSber.Repository.FavouriteRepo;
import com.example.CarShopSber.Service.CarService.CarServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Реализация сервиса для работы с избранными автомобилями пользователя.
 */
@Service
public class FavouriteServiceImpl implements FavouriteService {
    private static final Logger logger = LoggerFactory.getLogger(FavouriteServiceImpl.class);
    @Autowired
    private FavouriteRepo favouriteRepo;

    /**
     * Добавляет автомобиль в избранное пользователя.
     *
     * @param user Пользователь, которому принадлежит избранное.
     * @param car  Автомобиль, который нужно добавить в избранное.
     * @return Избранное пользователя после добавления автомобиля или null, если автомобиль уже в избранном.
     */
    @Override
    public Favourite addToFavorites(User user, Car car) {
        logger.info("Пользователь {} пытается добавить в избранное {}", user.getUsername(), car.getId());
        Favourite favourite = user.getFavourite();
        if (favourite == null) {
            favourite = new Favourite();
            favourite.setUserFavourite(user);
        }
        if (favourite.getFavoriteCars().contains(car)) {
            return null;
        } else {
            favourite.getFavoriteCars().add(car);
            favouriteRepo.save(favourite);
            return favourite;
        }
    }

    /**
     * Получает список избранных автомобилей пользователя.
     *
     * @param user Пользователь, чьи избранные автомобили нужно получить.
     * @return Список избранных автомобилей пользователя или пустой список, если у пользователя нет избранных автомобилей.
     */
    @Override
    public List<Car> getFavoriteCars(User user) {
        logger.info("Пользователь {} запрашивает все авто из избранного", user.getUsername());
        Favourite favorite = user.getFavourite();
        if (favorite != null) {
            return favorite.getFavoriteCars();
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Удаляет автомобиль из избранного пользователя.
     *
     * @param user  Пользователь, у которого нужно удалить автомобиль из избранного.
     * @param carId Идентификатор автомобиля, который нужно удалить из избранного.
     */
    @Override
    public void removeCarFromFavorites(User user, UUID carId) {
        logger.info("Пользователь {} удаляет автомобиль {} из избранного", user.getUsername(), carId);
        Favourite favorite = user.getFavourite();
        if (favorite != null) {
            List<Car> favoriteCars = favorite.getFavoriteCars();
            favoriteCars.removeIf(car -> car.getId().equals(carId));
            favouriteRepo.save(favorite);
        }
    }
}
