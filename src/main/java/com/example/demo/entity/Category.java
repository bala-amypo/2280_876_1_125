package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;
    private Boolean active = true;

    @ManyToMany(mappedBy = "categories")
    private Set<MenuItem> menuItems = new HashSet<>();

    // getters & setters
}
