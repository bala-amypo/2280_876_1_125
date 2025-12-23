package com.example.demo.service.impl;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.RecipeIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository repository;

    public RecipeIngredientServiceImpl(RecipeIngredientRepository repository) {
        this.repository = repository;
    }

    public RecipeIngredient addRecipeIngredient(RecipeIngredient recipeIngredient) {
        return repository.save(recipeIngredient);
    }

    public RecipeIngredient updateQuantity(Long id, Double quantity) {
        RecipeIngredient ri = repository.findById(id).orElseThrow();
        ri.setQuantityRequired(quantity);
        return repository.save(ri);
    }

    public List<RecipeIngredient> getByMenuItem(Long menuItemId) {
        return repository.findByMenuItemId(menuItemId);
    }

    public void deleteRecipeIngredient(Long id) {
        repository.deleteById(id);
    }
}
