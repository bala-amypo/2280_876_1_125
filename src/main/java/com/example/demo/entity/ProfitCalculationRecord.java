package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfitCalculationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MenuItem menuItem;

    private BigDecimal totalCost;
    private BigDecimal profitMargin;
    private Timestamp calculatedAt;

    @PrePersist
    void onCreate() {
        calculatedAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
}
