package com.example.demo.service;

import com.example.demo.entity.MenuItem;
import java.util.List;

public interface MenuItemService {
    MenuItem save(MenuItem menuItem);
    List<MenuItem> findAll();
}
