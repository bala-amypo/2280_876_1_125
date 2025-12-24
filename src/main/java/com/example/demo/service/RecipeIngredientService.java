package com.example.demo.service;

import com.example.demo.entity.RecipeIngredient;
import java.util.List;

public interface RecipeIngredientService {

    RecipeIngredient addRecipeIngredient(RecipeIngredient recipeIngredient);

    List<RecipeIngredient> getByMenuItem(Long menuItemId);

    RecipeIngredient updateRecipeIngredient(Long id, Double quantity);

    void removeRecipeIngredient(Long id);

    Double getTotalQuantityOfIngredient(Long ingredientId);
}
