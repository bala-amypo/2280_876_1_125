package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.service.IngredientService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    
    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        if (ingredient.getCostPerUnit() == null || ingredient.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Cost per unit must be greater than 0");
        }
        return ingredientRepository.save(ingredient);
    }
    
    @Override
    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        Ingredient existing = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));
        
        if (ingredient.getCostPerUnit() != null && ingredient.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Cost per unit must be greater than 0");
        }
        
        if (ingredient.getName() != null) existing.setName(ingredient.getName());
        if (ingredient.getUnit() != null) existing.setUnit(ingredient.getUnit());
        if (ingredient.getCostPerUnit() != null) existing.setCostPerUnit(ingredient.getCostPerUnit());
        if (ingredient.getActive() != null) existing.setActive(ingredient.getActive());
        
        return ingredientRepository.save(existing);
    }
    
    @Override
    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));
    }
    
    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
    
    @Override
    public Ingredient deactivateIngredient(Long id) {
        Ingredient ingredient = getIngredientById(id);
        ingredient.setActive(false);
        return ingredientRepository.save(ingredient);
    }
}