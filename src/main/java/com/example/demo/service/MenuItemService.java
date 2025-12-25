package com.example.demo.service;

import com.example.demo.entity.MenuItem;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem create(MenuItem menuItem) {

        menuItemRepository.findByNameIgnoreCase(menuItem.getName())
                .ifPresent(m -> {
                    throw new BadRequestException("Menu item already exists");
                });

        return menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getActiveMenuItems() {
        return menuItemRepository.findAllActiveWithCategories();
    }

    public MenuItem getById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Menu item not found"));
    }
}
