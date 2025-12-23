package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public Category addCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setName(category.getName());
        existing.setActive(category.getActive());
        return repository.save(existing);
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public void deactivateCategory(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setActive(false);
        repository.save(category);
    }
}
