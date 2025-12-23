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

    @Override
    public MenuItem save(MenuItem menuItem) {
        return repository.save(menuItem);
    }

    @Override
    public List<MenuItem> findAll() {
        return repository.findAll();
    }
}
