package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeIngredientRepository
        extends JpaRepository<RecipeIngredient, Long> {

    @Query("SELECT SUM(r.quantityRequired) FROM RecipeIngredient r WHERE r.ingredient.id = :ingredientId")
    Double getTotalQuantityByIngredientId(Long ingredientId);
}
