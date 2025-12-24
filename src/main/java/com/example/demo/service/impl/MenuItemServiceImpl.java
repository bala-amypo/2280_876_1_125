package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        MenuItem existing = getMenuItemById(id);
        existing.setName(menuItem.getName());
        existing.setPrice(menuItem.getSellingPrice()
);
        return menuItemRepository.save(existing);
    }

    @Override
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}
