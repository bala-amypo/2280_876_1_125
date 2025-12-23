package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.exception.*;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.service.IngredientService;
import java.util.List;

public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repo;

    public IngredientServiceImpl(IngredientRepository repo) {
        this.repo = repo;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        if (ingredient.getCostPerUnit().doubleValue() <= 0)
            throw new BadRequestException("Cost per unit");

        return repo.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        Ingredient existing = getIngredientById(id);
        existing.setName(ingredient.getName());
        existing.setUnit(ingredient.getUnit());
        existing.setCostPerUnit(ingredient.getCostPerUnit());
        return repo.save(existing);
    }

    public Ingredient getIngredientById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<Ingredient> getAllIngredients() {
        return repo.findAll();
    }

    public void deactivateIngredient(Long id) {
        Ingredient i = getIngredientById(id);
        i.setActive(false);
        repo.save(i);
    }
}
