package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Category;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category updateCategory(Long id, Category category);

    void deactivateCategory(Long id);
}
