package com.example.demo.entity;


import jakarta.persistence.*;
import java.util.Set;


@Entity
@Table(name = "categories", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(nullable = false, unique = true)
private String name;


private String description;


private Boolean active = true;


@ManyToMany(mappedBy = "categories")
private Set<MenuItem> menuItems;


// getters and setters
}