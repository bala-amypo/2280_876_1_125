package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.MenuItem;
import com.example.demo.service.MenuItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping
    public MenuItem create(@RequestBody MenuItem item) {
        return menuItemService.createMenuItem(item);
    }

    @PutMapping("/{id}")
    public MenuItem update(@PathVariable Long id, @RequestBody MenuItem item) {
        return menuItemService.updateMenuItem(id, item);
    }

    @GetMapping("/{id}")
    public MenuItem get(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @GetMapping
    public List<MenuItem> getAll() {
        return menuItemService.getAllMenuItems();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        menuItemService.deactivateMenuItem(id);
    }
}
