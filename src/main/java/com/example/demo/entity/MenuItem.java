package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price; // <-- Make sure this field exists

    public MenuItem() {}

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() { // <-- Getter for price
        return price;
    }

    public void setPrice(Double price) { // <-- Setter for price
        this.price = price;
    }
}
