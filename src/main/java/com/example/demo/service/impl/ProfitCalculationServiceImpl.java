package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ProfitCalculationService;
import java.math.BigDecimal;
import java.util.List;

public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuRepo;
    private final RecipeIngredientRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final ProfitCalculationRecordRepository profitRepo;

    public ProfitCalculationServiceImpl(
            MenuItemRepository m,
            RecipeIngredientRepository r,
            IngredientRepository i,
            ProfitCalculationRecordRepository p) {
        this.menuRepo = m;
        this.recipeRepo = r;
        this.ingredientRepo = i;
        this.profitRepo = p;
    }

    public ProfitCalculationRecord calculateProfit(Long menuItemId) {
        MenuItem menu = menuRepo.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        List<RecipeIngredient> list = recipeRepo.findByMenuItemId(menuItemId);

        BigDecimal totalCost = BigDecimal.ZERO;
        for (RecipeIngredient r : list) {
            BigDecimal cost = r.getIngredient().getCostPerUnit()
                    .multiply(BigDecimal.valueOf(r.getQuantityRequired()));
            totalCost = totalCost.add(cost);
        }

        BigDecimal profit = menu.getSellingPrice().subtract(totalCost);

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menu);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profit);

        return profitRepo.save(record);
    }

    public ProfitCalculationRecord getCalculationById(Long id) {
        return profitRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId) {
        return profitRepo.findByMenuItemId(menuItemId);
    }

    public List<ProfitCalculationRecord> getAllCalculations() {
        return profitRepo.findAll();
    }
}
