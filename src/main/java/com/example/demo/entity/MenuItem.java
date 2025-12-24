package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private BigDecimal sellingPrice;

    private boolean active = true;   // ✅ REQUIRED

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public boolean isActive() {      // ✅ REQUIRED
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
