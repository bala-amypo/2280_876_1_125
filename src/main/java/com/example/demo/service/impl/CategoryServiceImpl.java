package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        category.setActive(true);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existing = getCategoryById(id);
        existing.setName(category.getName());
        return categoryRepository.save(existing);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deactivateCategory(Long id) {
        Category category = getCategoryById(id);
        category.setActive(false);
        categoryRepository.save(category);
    }
}
