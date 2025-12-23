package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

        List<RecipeIngredient> recipes = recipeIngredientRepository.findByMenuItem(menu);
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
    public List<ProfitCalculationRecord> getAllProfitRecords() {
        return new ArrayList<>(profitCalculationRecordRepository.findAll());
    }
}
