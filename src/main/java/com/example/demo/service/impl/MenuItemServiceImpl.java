package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository repository;

    @Override
    public MenuItem addMenuItem(MenuItem item) {
        return repository.save(item);
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem item) {
        MenuItem existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        existing.setName(item.getName());
        existing.setDescription(item.getDescription());
        existing.setSellingPrice(item.getSellingPrice());
        existing.setActive(item.getActive());
        return repository.save(existing);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return repository.findAll();
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
    }

    @Override
    public void deactivateMenuItem(Long id) {
        MenuItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        item.setActive(false);
        repository.save(item);
    }
}
