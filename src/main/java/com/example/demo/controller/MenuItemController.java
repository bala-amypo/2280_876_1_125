package com.example.demo.controller;

import com.example.demo.entity.MenuItem;
import com.example.demo.service.MenuItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@Tag(name = "Menu Items", description = "Menu item management endpoints")
public class MenuItemController {
    private final MenuItemService menuItemService;
    
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }
    
    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        return ResponseEntity.ok(menuItemService.createMenuItem(menuItem));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return ResponseEntity.ok(menuItemService.updateMenuItem(id, menuItem));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.getMenuItemById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<MenuItem> deactivateMenuItem(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.deactivateMenuItem(id));
    }
}