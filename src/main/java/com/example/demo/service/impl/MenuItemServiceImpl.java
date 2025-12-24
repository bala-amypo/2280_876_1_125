package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.entity.MenuItem;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.MenuItemService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final CategoryRepository categoryRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository,
                               RecipeIngredientRepository recipeIngredientRepository,
                               CategoryRepository categoryRepository) {
        this.menuItemRepository = menuItemRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {

        menuItemRepository.findByNameIgnoreCase(menuItem.getName())
                .ifPresent(m -> {
                    throw new BadRequestException("Menu item already exists");
                });

        if (menuItem.getSellingPrice() == null ||
                menuItem.getSellingPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Selling price must be greater than zero");
        }

        Set<Category> validatedCategories = new HashSet<>();
        for (Category c : menuItem.getCategories()) {
            Category dbCat = categoryRepository.findById(c.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            if (!dbCat.isActive()) {
                throw new BadRequestException("Category is inactive");
            }
            validatedCategories.add(dbCat);
        }

        menuItem.setCategories(validatedCategories);
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem updated) {

        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        if (updated.isActive() &&
                !recipeIngredientRepository.existsByMenuItemId(id)) {
            throw new BadRequestException("Menu item cannot be active without recipe ingredients");
        }

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setSellingPrice(updated.getSellingPrice());
        existing.setActive(updated.isActive());

        if (updated.getCategories() != null) {
            Set<Category> validated = new HashSet<>();
            for (Category c : updated.getCategories()) {
                Category db = categoryRepository.findById(c.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
                if (!db.isActive()) {
                    throw new BadRequestException("Category is inactive");
                }
                validated.add(db);
            }
            existing.setCategories(validated);
        }

        return menuItemRepository.save(existing);
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public void deactivateMenuItem(Long id) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
        item.setActive(false);
        menuItemRepository.save(item);
    }

    // ✅ REQUIRED BY TESTS / CONTROLLER
    @Override
    public void deleteMenuItem(Long id) {
        MenuItem item = getMenuItemById(id);
        item.setActive(false);
        menuItemRepository.save(item);
    }
}
