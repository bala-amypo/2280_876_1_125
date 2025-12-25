package com.example.demo.service;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfitService {

    private final MenuItemRepository menuItemRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ProfitCalculationRecordRepository recordRepository;

    public ProfitService(MenuItemRepository menuItemRepository,
                         RecipeIngredientRepository recipeIngredientRepository,
                         ProfitCalculationRecordRepository recordRepository) {
        this.menuItemRepository = menuItemRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recordRepository = recordRepository;
    }

    public ProfitCalculationRecord calculateProfit(Long menuItemId) {

        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Menu item not found"));

        List<RecipeIngredient> ingredients =
                recipeIngredientRepository.findByMenuItemId(menuItemId);

        double totalCost = ingredients.stream()
                .mapToDouble(ri ->
                        ri.getQuantity() * ri.getIngredient().getCostPerUnit())
                .sum();

        double profit = menuItem.getPrice() - totalCost;
        double margin = (profit / menuItem.getPrice()) * 100;

        ProfitCalculationRecord record =
                new ProfitCalculationRecord();

        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfit(profit);
        record.setProfitMargin(margin);
        record.setCalculatedAt(LocalDateTime.now());

        return recordRepository.save(record);
    }
}
