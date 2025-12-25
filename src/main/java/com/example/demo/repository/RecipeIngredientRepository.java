package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    List<RecipeIngredient> findByMenuItemId(Long menuItemId);

    boolean existsByMenuItemId(Long menuItemId);

    @Query("SELECT COALESCE(SUM(r.quantity),0) FROM RecipeIngredient r WHERE r.ingredient.id = :ingredientId")
    Double getTotalQuantityByIngredientId(Long ingredientId);
}
