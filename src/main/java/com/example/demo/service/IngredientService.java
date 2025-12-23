package com.example.demo.service;

import com.example.demo.entity.Ingredient;

import java.util.List;

public interface IngredientService {

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient updateIngredient(Long id, Ingredient ingredient);

    List<Ingredient> getAllIngredients();

    Ingredient getIngredientById(Long id);

    void deactivateIngredient(Long id);
}
