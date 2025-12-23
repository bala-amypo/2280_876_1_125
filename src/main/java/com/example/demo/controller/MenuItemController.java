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
    public MenuItem save(@RequestBody MenuItem menuItem) {
        return service.save(menuItem);
    }

    @GetMapping
    public List<MenuItem> findAll() {
        return service.findAll();
    }
}
