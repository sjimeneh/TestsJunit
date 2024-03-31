package com.example.Mockito.service;

import com.example.Mockito.model.User;
import com.example.Mockito.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplementTest {
    @Mock
    UserRepository _userRepository;

    @InjectMocks
    UserServiceImplement _userServiceImplement;

    User user;
    List<User> allUser;

    @BeforeEach
    void setUp() {
        allUser = new ArrayList<>();
        user =
                new User(1L,
                        "Samuel Jimenez",
                        "samuelhz@gmail.com",
                        LocalDateTime.now(),
                        LocalDate.of(1998,2,11)
                );

        allUser.add(user);

    }

    @Test
    void getUserById() {
        Mockito.when(_userRepository.findById(1L)).thenReturn(Optional.of(user));
        User userFind = _userServiceImplement.getUserById(1L);
        Assertions.assertNotNull(userFind);
        Assertions.assertEquals(user,userFind);
    }

    @Test
    void getUsers() {
        Mockito.when(_userRepository.findAll()).thenReturn(allUser);
        List<User> usersFind = _userServiceImplement.getUsers();
        Assertions.assertNotNull(usersFind);
        Assertions.assertNotEquals(0,usersFind.size());
        Assertions.assertEquals(allUser,usersFind);
    }

    @Test
    void saveUser() {
        Mockito.when(_userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User userSaved = _userServiceImplement.saveUser(user);
        Assertions.assertNotNull(userSaved);
        Assertions.assertEquals(user,userSaved);
    }

    @Test
    void getByEmail() {
        Mockito
                .when(_userRepository.findUserByEmail("samuelhz@gmail.com"))
                .thenReturn(Optional.of(user));
        User userByEmail = _userServiceImplement.getByEmail("samuelhz@gmail.com");
        Assertions.assertNotNull(userByEmail);
        Assertions.assertEquals(user,userByEmail);
    }
}