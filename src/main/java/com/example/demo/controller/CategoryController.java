package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories", description = "Category management endpoints")
public class CategoryController {
    private final CategoryService categoryService;
    
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Category> deactivateCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.deactivateCategory(id));
    }
}