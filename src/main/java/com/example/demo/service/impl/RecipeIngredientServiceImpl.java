package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.RecipeIngredientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeRepo;
    private final MenuItemRepository menuRepo;
    private final IngredientRepository ingredientRepo;

    @Override
    public RecipeIngredient addIngredientToRecipe(Long menuItemId, Long ingredientId, Double quantity) {
        MenuItem menuItem = menuRepo.findById(menuItemId).orElseThrow();
        Ingredient ingredient = ingredientRepo.findById(ingredientId).orElseThrow();

        RecipeIngredient recipe = new RecipeIngredient();
        recipe.setMenuItem(menuItem);
        recipe.setIngredient(ingredient);
        recipe.setQuantity(quantity);

        return recipeRepo.save(recipe);
    }

    @Override
    public RecipeIngredient updateRecipeIngredient(Long id, Double quantity) {
        RecipeIngredient recipe = recipeRepo.findById(id).orElseThrow();
        recipe.setQuantity(quantity);
        return recipeRepo.save(recipe);
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
        return recipeRepo.sumQuantityByIngredientId(ingredientId);
    }
}
