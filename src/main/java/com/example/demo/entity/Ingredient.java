package com.example.demo.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "ingredients", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Ingredient {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(nullable = false, unique = true)
private String name;


private String unit;


@Column(nullable = false)
private BigDecimal costPerUnit;


private Boolean active = true;


private LocalDateTime createdAt;
private LocalDateTime updatedAt;


@PrePersist
protected void onCreate() {
createdAt = LocalDateTime.now();
updatedAt = LocalDateTime.now();
}


@PreUpdate
protected void onUpdate() {
updatedAt = LocalDateTime.now();
}


// getters and setters
}