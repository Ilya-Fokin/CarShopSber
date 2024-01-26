package com.example.CarShopSber.Controllers;

import com.example.CarShopSber.Entities.Car;
import com.example.CarShopSber.Entities.Favourite;
import com.example.CarShopSber.Entities.User;
import com.example.CarShopSber.Service.CarService.CarServiceImpl;
import com.example.CarShopSber.Service.FavouriteService.FavouriteServiceImpl;
import com.example.CarShopSber.Service.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Контроллер для работы с избранными автомобилями пользователя.
 */
@RestController
public class FavouriteController {

    @Autowired
    private FavouriteServiceImpl favouriteService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CarServiceImpl carService;

    /**
     * Добавляет автомобиль в избранное пользователя.
     *
     * @param id    Идентификатор добавляемого автомобиля.
     * @return      HTTP-ответ с результатом операции.
     */
    @PostMapping("/favourite/add")
    public ResponseEntity<String> addFavourite(@RequestParam String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if (user == null) {
            return new ResponseEntity<>("{\"message\":\"Ошибка: сессия не найдена, необходима авторизация\"}", HttpStatus.BAD_REQUEST);
        } else {
            Car car = carService.findById(UUID.fromString(id));
            Favourite favourite = favouriteService.addToFavorites(user, car);
            if (favourite == null) {
                return new ResponseEntity<>("{\"message\":\"Публикация уже есть в закладках\"}", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("{\"message\":\"Публикация теперь у вас в закладках\"}", HttpStatus.OK);
        }
    }

    /**
     * Возвращает список всех избранных автомобилей пользователя.
     *
     * @return Список избранных автомобилей.
     */
    @GetMapping("/favourite/get_all")
    public List<Car> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        return favouriteService.getFavoriteCars(user);
    }

    /**
     * Удаляет автомобиль из избранного пользователя.
     *
     * @param id    Идентификатор удаляемого автомобиля.
     * @return      HTTP-ответ с результатом операции.
     */
    @PostMapping("/favourite/drop")
    public ResponseEntity<String> drop(@RequestParam String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if (user == null) {
            return new ResponseEntity<>("{\"message\":\"Ошибка: сессия не найдена, необходима авторизация\"}", HttpStatus.BAD_REQUEST);
        } else {
            favouriteService.removeCarFromFavorites(user, UUID.fromString(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
