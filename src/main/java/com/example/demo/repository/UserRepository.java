package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // For login by username
    Optional<User> findByUsername(String username);

    // For login by email (ignore case)
    Optional<User> findByEmailIgnoreCase(String email);

    // Check if username or email exists
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
