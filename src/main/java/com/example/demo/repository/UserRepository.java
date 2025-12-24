package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by username (for login)
    Optional<User> findByUsername(String username);

    // Find a user by email (case-insensitive)
    Optional<User> findByEmailIgnoreCase(String email);

    // Check if username or email already exists
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
