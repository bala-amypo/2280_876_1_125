package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.MenuItem;

public interface MenuItemService {

    MenuItem createMenuItem(MenuItem item);

    MenuItem updateMenuItem(Long id, MenuItem item);

    MenuItem getMenuItemById(Long id);

    List<MenuItem> getAllMenuItems();

    void deactivateMenuItem(Long id);
}
