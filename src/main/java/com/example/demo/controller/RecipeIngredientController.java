package com.example.demo.controller;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.service.RecipeIngredientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipe-ingredients")
@Tag(name = "Recipe Ingredients", description = "Recipe ingredient management endpoints")
public class RecipeIngredientController {
    private final RecipeIngredientService recipeIngredientService;
    
    public RecipeIngredientController(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }
    
    @PostMapping
    public ResponseEntity<RecipeIngredient> addIngredientToRecipe(@RequestBody Map<String, Object> request) {
        Long menuItemId = Long.valueOf(request.get("menuItemId").toString());
        Long ingredientId = Long.valueOf(request.get("ingredientId").toString());
        Double quantity = Double.valueOf(request.get("quantity").toString());
        return ResponseEntity.ok(recipeIngredientService.addIngredientToRecipe(menuItemId, ingredientId, quantity));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RecipeIngredient> updateRecipeIngredient(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        return ResponseEntity.ok(recipeIngredientService.updateRecipeIngredient(id, request.get("quantity")));
    }
    
    @GetMapping("/menu-item/{menuItemId}")
    public ResponseEntity<List<RecipeIngredient>> getIngredientsByMenuItem(@PathVariable Long menuItemId) {
        return ResponseEntity.ok(recipeIngredientService.getIngredientsByMenuItem(menuItemId));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> removeIngredientFromRecipe(@PathVariable Long id) {
        recipeIngredientService.removeIngredientFromRecipe(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Recipe ingredient removed successfully");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/ingredient/{ingredientId}/total-quantity")
    public ResponseEntity<Map<String, Double>> getTotalQuantity(@PathVariable Long ingredientId) {
        Map<String, Double> response = new HashMap<>();
        response.put("totalQuantity", recipeIngredientService.getTotalQuantityOfIngredient(ingredientId));
        return ResponseEntity.ok(response);
    }
}