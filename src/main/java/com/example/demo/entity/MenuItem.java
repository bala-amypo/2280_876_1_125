package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double sellingPrice;

    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;   //  THIS WAS MISSING
}
