package com.example.Mockito.repository;

import com.example.Mockito.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestEntityManager testEntityManager;

    User user;

    @BeforeEach
    void setUp() {
        // Insertamos este registro en la base de datos en memoría
        user  = User.builder()
                .name("David Hernandez")
                .registerDate(LocalDateTime.now())
                .email("david.hernandez@gmail.com")
                .dateOfBirth(LocalDate.of(1998,2,11))
                .build();

        /* Indicamos con qué clase queremos tener una persistencia de datos */
        testEntityManager.persist(user);
    }

    @Test
    public void findUserByEmail(){
        User userByEmail = userRepository.findUserByEmail("david.hernandez@gmail.com").orElse(null);
        assertNotNull(userByEmail);
        assertEquals(userByEmail,user);
    }

    @Test
    public void findUserByEmailFail(){
        User userByEmail = userRepository.findUserByEmail("no.existe@gmail.com").orElse(null);
        assertNull(userByEmail);
    }

    @Test
    public void saveUser(){
        User newUser = User.builder()
                .name("samuel jimenez")
                .email("samuel.jimenez@gmail.com")
                .dateOfBirth(LocalDate.of(2000,5,14))
                .registerDate(LocalDateTime.now())
                .build();

        User resulUserSave = userRepository.save(newUser);

        System.out.println("USUARIO CREADO = "+resulUserSave.toString());

        assertNotNull(resulUserSave);
        assertEquals(newUser,resulUserSave);


    }
}