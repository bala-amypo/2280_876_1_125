package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    @Override
    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        Ingredient existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        existing.setName(ingredient.getName());
        existing.setUnit(ingredient.getUnit());
        existing.setCostPerUnit(ingredient.getCostPerUnit());
        existing.setActive(ingredient.getActive());
        return repository.save(existing);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return repository.findAll();
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }

    @Override
    public void deactivateIngredient(Long id) {
        Ingredient ingredient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        ingredient.setActive(false);
        repository.save(ingredient);
    }
}
