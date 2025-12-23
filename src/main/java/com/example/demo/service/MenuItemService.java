package com.example.demo.service;

import com.example.demo.entity.MenuItem;

import java.util.List;

public interface MenuItemService {

    MenuItem addMenuItem(MenuItem item);

    MenuItem updateMenuItem(Long id, MenuItem item);

    List<MenuItem> getAllMenuItems();

    MenuItem getMenuItemById(Long id);

    void deactivateMenuItem(Long id);
}
