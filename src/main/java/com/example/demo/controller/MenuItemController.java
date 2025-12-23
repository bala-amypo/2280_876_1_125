package com.example.demo.controller;

import com.example.demo.entity.MenuItem;
import com.example.demo.service.MenuItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService service;

    public MenuItemController(MenuItemService service) {
        this.service = service;
    }

    @PostMapping
    public MenuItem create(@RequestBody MenuItem menuItem) {
        return service.createMenuItem(menuItem);
    }

    @GetMapping
    public List<MenuItem> getAll() {
        return service.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public MenuItem getById(@PathVariable Long id) {
        return service.getMenuItemById(id);
    }

    @PutMapping("/{id}")
    public MenuItem update(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return service.updateMenuItem(id, menuItem);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateMenuItem(id);
    }
}
