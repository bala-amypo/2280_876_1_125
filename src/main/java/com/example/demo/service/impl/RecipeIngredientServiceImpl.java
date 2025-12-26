package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.RecipeIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final MenuItemRepository menuItemRepository;
    
    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeIngredientRepository,
                                      IngredientRepository ingredientRepository,
                                      MenuItemRepository menuItemRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.menuItemRepository = menuItemRepository;
    }
    
    @Override
    public RecipeIngredient addIngredientToRecipe(Long menuItemId, Long ingredientId, Double quantity) {
        if (quantity == null || quantity <= 0) {
            throw new BadRequestException("Quantity required must be greater than 0");
        }
        
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found"));
        
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));
        
        if (!ingredient.getActive()) {
            throw new BadRequestException("Only active ingredients can be used in recipes");
        }
        
        RecipeIngredient recipeIngredient = new RecipeIngredient(menuItem, ingredient, quantity);
        return recipeIngredientRepository.save(recipeIngredient);
    }
    
    @Override
    public RecipeIngredient updateRecipeIngredient(Long id, Double quantity) {
        if (quantity == null || quantity <= 0) {
            throw new BadRequestException("Quantity required must be greater than 0");
        }
        
        RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RecipeIngredient not found"));
        
        recipeIngredient.setQuantityRequired(quantity);
        return recipeIngredientRepository.save(recipeIngredient);
    }
    
    @Override
    public List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId) {
        return recipeIngredientRepository.findByMenuItemId(menuItemId);
    }
    
    @Override
    public void removeIngredientFromRecipe(Long id) {
        if (!recipeIngredientRepository.existsById(id)) {
            throw new ResourceNotFoundException("RecipeIngredient not found");
        }
        recipeIngredientRepository.deleteById(id);
    }
    
    @Override
    public Double getTotalQuantityOfIngredient(Long ingredientId) {
        return recipeIngredientRepository.getTotalQuantityByIngredientId(ingredientId);
    }
}