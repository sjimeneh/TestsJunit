package com.example.Mockito.service;

import com.example.Mockito.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public interface IUserService {
    User getUserById(Long id);
    List<User> getUsers();
    User saveUser(User user);

    User getByEmail(String email);
}
