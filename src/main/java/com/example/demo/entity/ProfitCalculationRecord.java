package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class ProfitCalculationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MenuItem menuItem;

    private BigDecimal totalCost;

    private Double profitMargin;   // ✅ MUST BE Double

    public Double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(Double profitMargin) {
        this.profitMargin = profitMargin;
    }
}
