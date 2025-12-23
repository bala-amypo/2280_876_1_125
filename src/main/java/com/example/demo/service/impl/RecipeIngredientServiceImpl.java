package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecipeIngredientService;
import java.util.List;

public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final MenuItemRepository menuRepo;

    public RecipeIngredientServiceImpl(
            RecipeIngredientRepository r,
            IngredientRepository i,
            MenuItemRepository m) {
        this.recipeRepo = r;
        this.ingredientRepo = i;
        this.menuRepo = m;
    }

    public RecipeIngredient addIngredientToRecipe(Long menuItemId, Long ingredientId, Double quantity) {
        if (quantity <= 0)
            throw new BadRequestException("quantity");

        Ingredient ing = ingredientRepo.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (!ing.getActive())
            throw new BadRequestException("inactive ingredient");

        MenuItem menu = menuRepo.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        RecipeIngredient r = new RecipeIngredient();
        r.setIngredient(ing);
        r.setMenuItem(menu);
        r.setQuantityRequired(quantity);
        return recipeRepo.save(r);
    }

    public RecipeIngredient updateRecipeIngredient(Long id, Double quantity) {
        RecipeIngredient r = recipeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        r.setQuantityRequired(quantity);
        return recipeRepo.save(r);
    }

    public List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId) {
        return recipeRepo.findByMenuItemId(menuItemId);
    }

    public void removeIngredientFromRecipe(Long id) {
        recipeRepo.deleteById(id);
    }

    public Double getTotalQuantityOfIngredient(Long ingredientId) {
        return recipeRepo.getTotalQuantityByIngredientId(ingredientId);
    }
}
