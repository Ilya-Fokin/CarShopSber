package com.example.CarShopSber.Service.RoleService;

import com.example.CarShopSber.Entities.Role;
import com.example.CarShopSber.Repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name).orElse(null);
    }
}
