package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserRepository userRepository,
                          JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setFullName(request.fullName);
        user.setEmail(request.email);
        user.setPassword(request.password);
        user.setRole(request.role);

        userRepository.save(user);

        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        User user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(request.email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        return jwtTokenProvider.createToken(
                user.getEmail(),
                user.getRole()
        );
    }
}
