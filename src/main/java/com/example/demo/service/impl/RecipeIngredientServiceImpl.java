package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeRepo;
    private final MenuItemRepository menuRepo;
    private final IngredientRepository ingredientRepo;

    public RecipeIngredientServiceImpl(
            RecipeIngredientRepository recipeRepo,
            MenuItemRepository menuRepo,
            IngredientRepository ingredientRepo) {
        this.recipeRepo = recipeRepo;
        this.menuRepo = menuRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public RecipeIngredient addIngredientToRecipe(Long menuItemId, Long ingredientId, Double quantity) {
        MenuItem menuItem = menuRepo.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        Ingredient ingredient = ingredientRepo.findById(ingredientId)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        RecipeIngredient ri = new RecipeIngredient();
        ri.setMenuItem(menuItem);
        ri.setIngredient(ingredient);
        ri.setQuantity(quantity);

        return recipeRepo.save(ri);
    }

    @Override
    public RecipeIngredient updateRecipeIngredient(Long id, Double quantity) {
        RecipeIngredient ri = recipeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("RecipeIngredient not found"));
        ri.setQuantity(quantity);
        return recipeRepo.save(ri);
    }

    @Override
    public List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId) {
        return recipeRepo.findByMenuItemId(menuItemId);
    }

    @Override
    public void removeIngredientFromRecipe(Long id) {
        recipeRepo.deleteById(id);
    }

    @Override
    public Double getTotalQuantityOfIngredient(Long ingredientId) {
        return recipeRepo.findByIngredientId(ingredientId)
                .stream()
                .mapToDouble(RecipeIngredient::getQuantity)
                .sum();
    }
}
