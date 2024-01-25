package com.example.CarShopSber.Service.UserService;

import com.example.CarShopSber.Entities.Role;
import com.example.CarShopSber.Entities.User;
import com.example.CarShopSber.Repository.RoleRepo;
import com.example.CarShopSber.Repository.UserRepo;
import com.example.CarShopSber.Service.RoleService.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public User save(User user) {
        if (findByUsername(user.getUsername()) == null && findByMail(user.getMail()) == null) {
            Role role = roleService.findByName("ROLE_USER");
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singleton(role));
            return userRepo.save(user);
        } else return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

    @Override
    public User findByMail(String mail) {
        return userRepo.findByMail(mail);
    }
}
