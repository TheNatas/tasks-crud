package com.example.hellobackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.example.hellobackend.dto.AuthRequest;
import com.example.hellobackend.dto.AuthResponse;
import com.example.hellobackend.repository.UserRepository;
import com.example.hellobackend.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        var user = userRepository.findByUsername(request.username)
                .orElse(null);

        if (user == null || !passwordEncoder.matches(request.password, user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername(), user.getRole().name());
        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (!jwtUtil.validateToken(refreshToken)) {
            return ResponseEntity.status(401).body("Invalid refresh token");
        }

        String username = jwtUtil.extractUsername(refreshToken);
        String role = jwtUtil.extractRole(refreshToken);
        String newAccessToken = jwtUtil.generateToken(username, role);
        String newRefreshToken = jwtUtil.generateRefreshToken(username, role);

        return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/data")
    public String adminOnly() {
        return "Secret admin data";
    }

}
