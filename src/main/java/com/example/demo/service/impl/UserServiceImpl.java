package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public String register(RegisterRequest req) {
        if (repo.existsByEmail(req.email))
            throw new BadRequestException("Email already in use");

        User u = new User();
        u.setFullName(req.fullName);
        u.setEmail(req.email);
        u.setPassword(encoder.encode(req.password));
        u.setRole(req.role != null ? req.role : "MANAGER");

        repo.save(u);
        return "Registered";
    }

    public String login(AuthRequest req) {
        User u = repo.findByEmail(req.email);
        if (u == null || !encoder.matches(req.password, u.getPassword()))
            throw new BadRequestException("Invalid credentials");

        JwtTokenProvider jwt = new JwtTokenProvider("secret", 3600000);
        return jwt.createToken(u.getEmail());
    }
}
