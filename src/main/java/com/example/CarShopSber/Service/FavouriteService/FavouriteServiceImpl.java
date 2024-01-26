package com.example.CarShopSber.Service.FavouriteService;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.Favourite;
import com.example.CarShopSber.Entities.User;
import com.example.CarShopSber.Repository.FavouriteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FavouriteServiceImpl implements FavouriteService{
    @Autowired
    private FavouriteRepo favouriteRepo;

    public Favourite addToFavorites(User user, Car car) {
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

    public List<Car> getFavoriteCars(User user) {
        Favourite favorite = user.getFavourite();

        if (favorite != null) {
            return favorite.getFavoriteCars();
        } else {
            return Collections.emptyList();
        }
    }

    public void removeCarFromFavorites(User user, UUID carId) {
        Favourite favorite = user.getFavourite();
        if (favorite != null) {
            List<Car> favoriteCars = favorite.getFavoriteCars();
            favoriteCars.removeIf(car -> car.getId().equals(carId));
            favouriteRepo.save(favorite);
        }
    }
}
