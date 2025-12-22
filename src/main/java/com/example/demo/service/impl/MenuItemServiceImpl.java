package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.service.MenuItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    @Override
    public MenuItem createMenuItem(MenuItem item) {
        item.setActive(true);
        return menuItemRepository.save(item);
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem item) {
        MenuItem existing = getMenuItemById(id);
        existing.setName(item.getName());
        existing.setSellingPrice(item.getSellingPrice());
        return menuItemRepository.save(existing);
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public void deactivateMenuItem(Long id) {
        MenuItem item = getMenuItemById(id);
        item.setActive(false);
        menuItemRepository.save(item);
    }
}
