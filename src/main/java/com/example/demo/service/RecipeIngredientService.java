package com.example.demo.service;

import com.example.demo.entity.RecipeIngredient;
import java.util.List;

public interface RecipeIngredientService {
    RecipeIngredient addRecipeIngredient(RecipeIngredient recipeIngredient);
    RecipeIngredient updateQuantity(Long id, Double quantity);
    List<RecipeIngredient> getByMenuItem(Long menuItemId);
    void deleteRecipeIngredient(Long id);
}
