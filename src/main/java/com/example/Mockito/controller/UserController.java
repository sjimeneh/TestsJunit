package com.example.Mockito.controller;

import com.example.Mockito.model.User;
import com.example.Mockito.service.IUserService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    IUserService _iUserService;

    @PostMapping("/create")
    public ResponseEntity<?> PostUser(@Valid @RequestBody User user){
        User userResponse = _iUserService.saveUser(user);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<User>> findAllUsers() {
            List<User> users = _iUserService.getUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
            User user = _iUserService.getByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
