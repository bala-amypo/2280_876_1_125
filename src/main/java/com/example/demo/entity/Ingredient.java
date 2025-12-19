package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(
    name = "ingredient",
    uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String unit;

    private BigDecimal costPerUnit;

    private Boolean active = true;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public BigDecimal getCostPerUnit() { return costPerUnit; }
    public void setCostPerUnit(BigDecimal costPerUnit) { this.costPerUnit = costPerUnit; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
