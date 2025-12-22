package com.example.demo.repository;

import com.example.demo.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuItemRepository
        extends JpaRepository<MenuItem, Long> {

    @Query("SELECT DISTINCT m FROM MenuItem m LEFT JOIN FETCH m.categories")
    List<MenuItem> findAllActiveWithCategories();
}
