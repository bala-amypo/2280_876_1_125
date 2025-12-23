package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Ingredient;
import com.example.demo.service.IngredientService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
@Tag(name = "Ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public Ingredient create(@RequestBody Ingredient ingredient) {
        return ingredientService.createIngredient(ingredient);
    }

    @PutMapping("/{id}")
    public Ingredient update(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(id, ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient get(@PathVariable Long id) {
        return ingredientService.getIngredientById(id);
    }

    @GetMapping
    public List<Ingredient> getAll() {
        return ingredientService.getAllIngredients();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        ingredientService.deactivateIngredient(id);
    }
}
