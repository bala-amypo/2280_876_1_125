package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.MenuItemService;
import java.util.List;

public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuRepo;
    private final RecipeIngredientRepository recipeRepo;
    private final CategoryRepository categoryRepo;

    public MenuItemServiceImpl(MenuItemRepository m, RecipeIngredientRepository r, CategoryRepository c) {
        this.menuRepo = m;
        this.recipeRepo = r;
        this.categoryRepo = c;
    }

    public MenuItem createMenuItem(MenuItem item) {
        if (item.getSellingPrice().doubleValue() <= 0)
            throw new BadRequestException("selling price");

        return menuRepo.save(item);
    }

    public MenuItem updateMenuItem(Long id, MenuItem item) {
        MenuItem m = getMenuItemById(id);
        m.setName(item.getName());
        m.setDescription(item.getDescription());
        m.setSellingPrice(item.getSellingPrice());
        return menuRepo.save(m);
    }

    public MenuItem getMenuItemById(Long id) {
        return menuRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<MenuItem> getAllMenuItems() {
        return menuRepo.findAllActiveWithCategories();
    }

    public void deactivateMenuItem(Long id) {
        MenuItem m = getMenuItemById(id);
        m.setActive(false);
        menuRepo.save(m);
    }
}
