package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.ProfitCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final ProfitCalculationRecordRepository recordRepository;
    private final MenuItemRepository menuItemRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Override
    public ProfitCalculationRecord calculateProfit(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));

        List<RecipeIngredient> ingredients = recipeIngredientRepository.findByMenuItem(menuItem);

        BigDecimal totalCost = ingredients.stream()
                .map(i -> i.getIngredient().getCostPerUnit()
                        .multiply(BigDecimal.valueOf(i.getQuantityRequired())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal profitMargin = menuItem.getSellingPrice().subtract(totalCost);

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profitMargin);

        return recordRepository.save(record);
    }

    @Override
    public ProfitCalculationRecord getCalculationById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculation not found"));
    }

    @Override
    public List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        return recordRepository.findByMenuItem(menuItem);
    }

    @Override
    public List<ProfitCalculationRecord> getAllCalculations() {
        return recordRepository.findAll();
    }
}
