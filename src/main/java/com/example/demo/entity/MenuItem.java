package com.example.demo.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "menu_items", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class MenuItem {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(nullable = false, unique = true)
private String name;


private String description;


@Column(nullable = false)
private BigDecimal sellingPrice;


private Boolean active = true;


private LocalDateTime createdAt;
private LocalDateTime updatedAt;


@ManyToMany
@JoinTable(
name = "menuitem_category",
joinColumns = @JoinColumn(name = "menuitem_id"),
inverseJoinColumns = @JoinColumn(name = "category_id")
)
private Set<Category> categories;


@PrePersist
protected void onCreate() {
createdAt = LocalDateTime.now();
updatedAt = LocalDateTime.now();
}


@PreUpdate
protected void onUpdate() {
updatedAt = LocalDateTime.now();
}


// getters and setters
}