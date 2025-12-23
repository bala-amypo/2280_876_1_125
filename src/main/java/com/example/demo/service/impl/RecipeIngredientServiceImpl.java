package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.RecipeIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final MenuItemRepository menuItemRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public RecipeIngredient addIngredientToRecipe(Long menuItemId, Long ingredientId, Double quantity) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setMenuItem(menuItem);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setQuantityRequired(quantity);

        return recipeIngredientRepository.save(recipeIngredient);
    }

    @Override
    public RecipeIngredient updateRecipeIngredient(Long id, Double quantity) {
        RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RecipeIngredient not found"));
        recipeIngredient.setQuantityRequired(quantity);
        return recipeIngredientRepository.save(recipeIngredient);
    }

    @Override
    public List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        return recipeIngredientRepository.findByMenuItem(menuItem);
    }

    @Override
    public void removeIngredientFromRecipe(Long id) {
        if (!recipeIngredientRepository.existsById(id)) {
            throw new RuntimeException("RecipeIngredient not found");
        }
        recipeIngredientRepository.deleteById(id);
    }

    @Override
    public Double getTotalQuantityOfIngredient(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        return recipeIngredientRepository.findByIngredient(ingredient).stream()
                .mapToDouble(RecipeIngredient::getQuantityRequired)
                .sum();
    }
}
