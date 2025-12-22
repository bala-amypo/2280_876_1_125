package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.dto.*;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String register(RegisterRequest request) {
        return "User registered successfully";
    }

    @Override
    public String login(AuthRequest request) {
        return "Login successful";
    }
}
