package com.example.demo.repository;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    List<RecipeIngredient> findByMenuItem(MenuItem menuItem);

    @Query("SELECT SUM(r.quantityRequired) FROM RecipeIngredient r WHERE r.ingredient = :ingredient")
    Double sumQuantityByIngredient(@Param("ingredient") Ingredient ingredient);
}
