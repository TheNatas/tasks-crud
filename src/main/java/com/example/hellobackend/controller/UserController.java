package com.example.hellobackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hellobackend.model.User;
import com.example.hellobackend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User created = userService.registerUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(created);
    }
}
