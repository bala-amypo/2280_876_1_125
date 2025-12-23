package com.example.demo.service;

import com.example.demo.entity.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);

    Category updateCategory(Long id, Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    void deactivateCategory(Long id);
}
