package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category createCategory(Category category) {
        return repository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        Category existing = repository.findById(id).orElseThrow();
        existing.setName(category.getName());
        existing.setDescription(category.getDescription());
        return repository.save(existing);
    }

    public Category getCategoryById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public void deactivateCategory(Long id) {
        Category category = repository.findById(id).orElseThrow();
        category.setActive(false);
        repository.save(category);
    }
}
