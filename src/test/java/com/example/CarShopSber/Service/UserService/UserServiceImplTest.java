package com.example.CarShopSber.Service.UserService;

import com.example.CarShopSber.Entities.Role;
import com.example.CarShopSber.Entities.User;
import com.example.CarShopSber.Repository.UserRepo;
import com.example.CarShopSber.Service.RoleService.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.never;
import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepo userRepo;

    @Mock
    private RoleServiceImpl roleService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testSaveUser_Successful() {
        User user = new User("testUser", "Михайил", "Иванов", "test@example.com", "password", "+79372583349");
        Role role = new Role("ROLE_USER");

        when(roleService.findByName("ROLE_USER")).thenReturn(role);
        when(userRepo.findByUsername("testUser")).thenReturn(Optional.empty());
        when(userRepo.findByMail("test@example.com")).thenReturn(null);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        when(userRepo.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getMail(), savedUser.getMail());
        assertEquals(Collections.singleton(role), savedUser.getRoles());
    }

    @Test
    void testFindByUsername_UserExists() {
        User existingUser = new User("admin", "John", "Doe", "fokin3349@mail.ru", "password", "+123456789");
        when(userRepo.findByUsername("admin")).thenReturn(Optional.of(existingUser));

        User foundUser = userService.findByUsername("admin");

        assertNotNull(foundUser);
        assertEquals("fokin3349@mail.ru", foundUser.getMail());
    }

    @Test
    void testSaveUser_DuplicateMail() {
        User user = new User("testUser", "Михайил", "Иванов", "fokin3349@mail.ru", "password", "89372583349");

        when(userRepo.findByUsername("testUser")).thenReturn(Optional.empty());
        when(userRepo.findByMail("fokin3349@mail.ru")).thenReturn(user);

        User savedUser = userService.save(user);

        assertNull(savedUser);
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findByMail() {
    }
}