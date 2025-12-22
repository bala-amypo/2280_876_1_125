package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Category;

public interface CategoryService {

    Category createCategory(Category category);

    Category updateCategory(Long id, Category category);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    void deactivateCategory(Long id);
}
