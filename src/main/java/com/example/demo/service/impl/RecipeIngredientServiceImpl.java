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

    @Override
    public RecipeIngredient save(RecipeIngredient recipeIngredient) {
        return repository.save(recipeIngredient);
    }

    @Override
    public List<RecipeIngredient> findAll() {
        return repository.findAll();
    }
}
