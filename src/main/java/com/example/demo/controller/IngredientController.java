package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.service.IngredientServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    // Create ingredient
    @PostMapping
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.createIngredient(ingredient);
    }

    // Get ingredient by id
    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable Long id) {
        return ingredientService.getIngredientById(id);
    }

    // Get all ingredients
    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    // Update ingredient
    @PutMapping("/{id}")
    public Ingredient updateIngredient(
            @PathVariable Long id,
            @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(id, ingredient);
    }

    // Deactivate ingredient
    @PutMapping("/{id}/deactivate")
    public void deactivateIngredient(@PathVariable Long id) {
        ingredientService.deactivateIngredient(id);
    }
}
