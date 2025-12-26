package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.service.IngredientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@Tag(name = "Ingredients", description = "Ingredient management endpoints")
public class IngredientController {
    private final IngredientService ingredientService;
    
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    
    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.createIngredient(ingredient));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.updateIngredient(id, ingredient));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Ingredient> deactivateIngredient(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.deactivateIngredient(id));
    }
}