package com.example.Mockito.repository;

import com.example.Mockito.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
}
