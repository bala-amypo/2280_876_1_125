package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Ingredient;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.service.IngredientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredient.setActive(true);
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        Ingredient existing = getIngredientById(id);
        existing.setName(ingredient.getName());
        existing.setCost(ingredient.getCost());
        return ingredientRepository.save(existing);
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public void deactivateIngredient(Long id) {
        Ingredient ingredient = getIngredientById(id);
        ingredient.setActive(false);
        ingredientRepository.save(ingredient);
    }
}
