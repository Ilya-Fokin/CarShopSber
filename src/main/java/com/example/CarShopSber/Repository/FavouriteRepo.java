package com.example.CarShopSber.Repository;

import com.example.CarShopSber.Entities.Favourite;
import com.example.CarShopSber.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface FavouriteRepo extends CrudRepository<Favourite, UUID> {
}
