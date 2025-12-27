package com.example.demo.controller;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.service.RecipeIngredientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recipe-ingredients")
@Tag(name = "Recipe Ingredients")
@SecurityRequirement(name = "bearerAuth")
public class RecipeIngredientController {
    
    private final RecipeIngredientService recipeIngredientService;
    
    public RecipeIngredientController(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }
    
    @PostMapping
    public ResponseEntity<RecipeIngredient> addIngredientToMenuItem(@RequestBody RecipeIngredient recipeIngredient) {
        RecipeIngredient created = recipeIngredientService.addIngredientToMenuItem(recipeIngredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/ingredient/{ingredientId}/total-quantity")
    public ResponseEntity<Double> getTotalQuantityOfIngredient(@PathVariable Long ingredientId) {
        Double totalQuantity = recipeIngredientService.getTotalQuantityOfIngredient(ingredientId);
        return ResponseEntity.ok(totalQuantity);
    }
}