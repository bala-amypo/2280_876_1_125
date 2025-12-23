package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;

    public IngredientServiceImpl(IngredientRepository repository) {
        this.repository = repository;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        Ingredient existing = repository.findById(id).orElseThrow();
        existing.setName(ingredient.getName());
        existing.setUnit(ingredient.getUnit());
        existing.setCostPerUnit(ingredient.getCostPerUnit());
        return repository.save(existing);
    }

    public Ingredient getIngredientById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Ingredient> getAllIngredients() {
        return repository.findAll();
    }

    public void deactivateIngredient(Long id) {
        Ingredient ingredient = repository.findById(id).orElseThrow();
        ingredient.setActive(false);
        repository.save(ingredient);
    }
}
