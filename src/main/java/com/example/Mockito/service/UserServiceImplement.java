package com.example.Mockito.service;

import com.example.Mockito.model.User;
import com.example.Mockito.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImplement implements IUserService{
    @Autowired
    private UserRepository _userRepository;
    @Override
    public User getUserById(Long id) {
        return _userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        _userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User saveUser(User user) {
        return _userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return _userRepository.findUserByEmail(email).orElse(null);
    }
}
