package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository repository;

    public MenuItemServiceImpl(MenuItemRepository repository) {
        this.repository = repository;
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        return repository.save(menuItem);
    }

    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        MenuItem existing = repository.findById(id).orElseThrow();
        existing.setName(menuItem.getName());
        existing.setDescription(menuItem.getDescription());
        existing.setSellingPrice(menuItem.getSellingPrice());
        return repository.save(existing);
    }

    public MenuItem getMenuItemById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<MenuItem> getAllMenuItems() {
        return repository.findAll();
    }

    public void deactivateMenuItem(Long id) {
        MenuItem menuItem = repository.findById(id).orElseThrow();
        menuItem.setActive(false);
        repository.save(menuItem);
    }
}
