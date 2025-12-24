package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public interface UserService {
    User registerUser(UserDTO userDTO);
    User getUserByUsername(String username);
}
