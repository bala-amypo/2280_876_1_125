package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public interface UserService {
    User register(UserDTO userDTO);
    User getByEmail(String email);
    User getUserByUsername(String username);
}
