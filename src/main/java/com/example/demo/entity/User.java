package com.example.demo.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


private String fullName;


@Column(nullable = false, unique = true)
private String email;


@Column(nullable = false)
private String password;


@Column(nullable = false)
private String role = "MANAGER";


private LocalDateTime createdAt;


@PrePersist
protected void onCreate() {
createdAt = LocalDateTime.now();
}


// getters and setters
}