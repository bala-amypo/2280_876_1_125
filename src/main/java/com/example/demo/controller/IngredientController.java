package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.service.IngredientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@Tag(name = "Ingredients")
public class IngredientController {

    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @PostMapping
    public Ingredient create(@RequestBody Ingredient i) {
        return service.createIngredient(i);
    }

    @PutMapping("/{id}")
    public Ingredient update(@PathVariable Long id, @RequestBody Ingredient i) {
        return service.updateIngredient(id, i);
    }

    @GetMapping("/{id}")
    public Ingredient get(@PathVariable Long id) {
        return service.getIngredientById(id);
    }

    @GetMapping
    public List<Ingredient> all() {
        return service.getAllIngredients();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateIngredient(id);
    }
}
