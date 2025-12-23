package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.ProfitCalculationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuItemRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ProfitCalculationRecordRepository profitCalculationRecordRepository;

    @Override
    public ProfitCalculationRecord calculateProfit(Long menuItemId) {

        MenuItem menu = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        List<RecipeIngredient> recipes = recipeIngredientRepository.findAll()
                .stream()
                .filter(r -> r.getMenuItem().getId().equals(menuItemId))
                .collect(Collectors.toList());

        BigDecimal totalCost = BigDecimal.ZERO;
        for (RecipeIngredient r : recipes) {
            Ingredient ingredient = r.getIngredient();
            BigDecimal cost = ingredient.getCostPerUnit().multiply(BigDecimal.valueOf(r.getQuantityRequired()));
            totalCost = totalCost.add(cost);
        }

        BigDecimal profitMargin = menu.getSellingPrice().subtract(totalCost);

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menu);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profitMargin);

        return profitCalculationRecordRepository.save(record);
    }

    @Override
    public ProfitCalculationRecord getCalculationById(Long id) {
        return profitCalculationRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profit record not found"));
    }

    @Override
    public List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId) {
        return profitCalculationRecordRepository.findAll()
                .stream()
                .filter(r -> r.getMenuItem().getId().equals(menuItemId))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfitCalculationRecord> getAllCalculations() {
        return profitCalculationRecordRepository.findAll();
    }
}
