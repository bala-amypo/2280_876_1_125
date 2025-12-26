package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String unit;
    private BigDecimal costPerUnit;
    private Boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    // getters & setters
    // IMPORTANT: Boolean getActive()
}
