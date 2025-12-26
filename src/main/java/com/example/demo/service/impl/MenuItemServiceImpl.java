package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
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
        if (menuItem.getSellingPrice() == null || menuItem.getSellingPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Selling price must be greater than 0");
        }
        return menuItemRepository.save(menuItem);
    }
    
    @Override
    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found"));
        
        if (menuItem.getSellingPrice() != null && menuItem.getSellingPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Selling price must be greater than 0");
        }
        
        if (menuItem.getName() != null) existing.setName(menuItem.getName());
        if (menuItem.getDescription() != null) existing.setDescription(menuItem.getDescription());
        if (menuItem.getSellingPrice() != null) existing.setSellingPrice(menuItem.getSellingPrice());
        if (menuItem.getActive() != null) existing.setActive(menuItem.getActive());
        
        return menuItemRepository.save(existing);
    }
    
    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found"));
    }
    
    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }
    
    @Override
    public MenuItem deactivateMenuItem(Long id) {
        MenuItem menuItem = getMenuItemById(id);
        menuItem.setActive(false);
        return menuItemRepository.save(menuItem);
    }
}