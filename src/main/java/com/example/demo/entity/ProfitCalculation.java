package com.example.demo.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "profit_calculation_records")
public class ProfitCalculationRecord {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@ManyToOne(optional = false)
private MenuItem menuItem;


private BigDecimal totalCost;
private BigDecimal profitMargin;


private LocalDateTime calculatedAt;


@PrePersist
protected void onCalculate() {
calculatedAt = LocalDateTime.now();
}


// getters and setters
}