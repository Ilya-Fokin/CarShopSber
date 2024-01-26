package com.example.CarShopSber.Repository;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRepo extends CrudRepository<Car, UUID> {
    List<Car> findAll();
    Optional<Car> findByTitle(String title);
    Optional<Car> findById(UUID id);
    List<Car> findByUser(User user);

    List<Car> findAllByTitleContaining(String title);
}
