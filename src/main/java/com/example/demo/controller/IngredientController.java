package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.service.IngredientService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @PostMapping
    public Ingredient save(@RequestBody Ingredient ingredient) {
        return service.save(ingredient);
    }

    @GetMapping
    public List<Ingredient> findAll() {
        return service.findAll();
    }
}
