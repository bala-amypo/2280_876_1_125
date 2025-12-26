package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ProfitCalculationRecord {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private MenuItem menuItem;

    private BigDecimal totalCost;
    private Double profitMargin;

    private LocalDateTime calculatedAt = LocalDateTime.now();

    // getters & setters
}
