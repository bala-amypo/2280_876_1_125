package com.example.demo.service;

import com.example.demo.entity.Ingredient;
import java.util.List;

public interface IngredientService {
    Ingredient save(Ingredient ingredient);
    List<Ingredient> findAll();
}
