package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;

public interface UserService {

    User register(RegisterRequest request); // Register user

    User getByEmailIgnoreCase(String email); // Get user by email (case-insensitive)

    User getUserByUsername(String username); // Get user by username
}
