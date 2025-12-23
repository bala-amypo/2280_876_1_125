package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.RecipeIngredientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepository;

    @Override
    public RecipeIngredient addRecipeIngredient(RecipeIngredient r) {
        return recipeIngredientRepository.save(r);
    }

    @Override
    public RecipeIngredient updateRecipeIngredient(Long id, RecipeIngredient r) {
        RecipeIngredient existing = recipeIngredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RecipeIngredient not found"));
        existing.setIngredient(r.getIngredient());
        existing.setMenuItem(r.getMenuItem());
        existing.setQuantityRequired(r.getQuantityRequired());
        return recipeIngredientRepository.save(existing);
    }

    @Override
    public void deleteRecipeIngredient(Long id) {
        RecipeIngredient existing = recipeIngredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RecipeIngredient not found"));
        recipeIngredientRepository.delete(existing);
    }

    @Override
    public List<RecipeIngredient> getAllRecipeIngredients() {
        return recipeIngredientRepository.findAll();
    }

    @Override
    public RecipeIngredient getRecipeIngredientById(Long id) {
        return recipeIngredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RecipeIngredient not found"));
    }
}
