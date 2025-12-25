package com.example.demo.service;

import com.example.demo.entity.MenuItem;
import java.util.List;

public interface MenuItemService {
    MenuItem createMenuItem(MenuItem menuItem);
    MenuItem getMenuItem(Long id);
    List<MenuItem> getAllActiveMenuItems();
    MenuItem updateMenuItem(Long id, MenuItem menuItem);
    void deactivateMenuItem(Long id);
}
