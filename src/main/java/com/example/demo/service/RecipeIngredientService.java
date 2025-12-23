package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.RecipeIngredient;

public interface RecipeIngredientService {
    
    RecipeIngredient addRecipeIngredient(RecipeIngredient r);

    RecipeIngredient updateRecipeIngredient(Long id, RecipeIngredient r);

    void deleteRecipeIngredient(Long id);

    List<RecipeIngredient> getAllRecipeIngredients();

    RecipeIngredient getRecipeIngredientById(Long id);
}
