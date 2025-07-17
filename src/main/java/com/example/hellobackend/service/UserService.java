package com.example.hellobackend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.hellobackend.model.User;
import com.example.hellobackend.model.Role;
import com.example.hellobackend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String rawPassword) {
        String hashed = passwordEncoder.encode(rawPassword);
        User user = new User(username, hashed, Role.USER);
        return userRepository.save(user);
    }
}
