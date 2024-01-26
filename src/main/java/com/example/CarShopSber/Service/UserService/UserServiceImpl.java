package com.example.CarShopSber.Service.UserService;

import com.example.CarShopSber.Entities.Role;
import com.example.CarShopSber.Entities.User;
import com.example.CarShopSber.Repository.RoleRepo;
import com.example.CarShopSber.Repository.UserRepo;
import com.example.CarShopSber.Service.CarService.CarServiceImpl;
import com.example.CarShopSber.Service.RoleService.RoleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Реализация сервиса для работы с пользователями.
 */
@Service
public class UserServiceImpl implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleServiceImpl roleService;

    /**
     * Сохраняет пользователя в системе.
     *
     * @param user Пользователь для сохранения.
     * @return Сохраненный пользователь или null, если пользователь с таким именем пользователя или адресом электронной почты уже существует.
     */
    @Override
    public User save(User user) {
        logger.info("Попытка сохранения пользователя {}", user.toString());
        if (findByUsername(user.getUsername()) == null && findByMail(user.getMail()) == null) {
            Role role = roleService.findByName("ROLE_USER");
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singleton(role));
            return userRepo.save(user);
        } else return null;
    }

    /**
     * Находит пользователя по его имени пользователя.
     *
     * @param username Имя пользователя.
     * @return Найденный пользователь или null, если пользователь не найден.
     */
    @Override
    public User findByUsername(String username) {
        logger.info("Поиск пользователя по username {}", username);
        return userRepo.findByUsername(username).orElse(null);
    }

    /**
     * Находит пользователя по его адресу электронной почты.
     *
     * @param mail Адрес электронной почты пользователя.
     * @return Найденный пользователь или null, если пользователь не найден.
     */
    @Override
    public User findByMail(String mail) {
        logger.info("Поиск пользователя по mail {}", mail);
        return userRepo.findByMail(mail);
    }
}
