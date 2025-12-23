package com.example.demo.service;

import com.example.demo.entity.RecipeIngredient;
import java.util.List;

public interface RecipeIngredientService {
    RecipeIngredient save(RecipeIngredient recipeIngredient);
    List<RecipeIngredient> findAll();
}
