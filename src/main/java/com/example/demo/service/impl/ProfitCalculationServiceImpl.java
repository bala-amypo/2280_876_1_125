package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ProfitCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuItemRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ProfitCalculationRecordRepository recordRepository;

    public ProfitCalculationServiceImpl(
            MenuItemRepository menuItemRepository,
            RecipeIngredientRepository recipeIngredientRepository,
            ProfitCalculationRecordRepository recordRepository) {

        this.menuItemRepository = menuItemRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public ProfitCalculationRecord calculateProfit(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        List<RecipeIngredient> recipeIngredients =
                recipeIngredientRepository.findByMenuItemId(menuItemId);

        if (recipeIngredients.isEmpty()) {
            throw new BadRequestException("No recipe ingredients found");
        }

        BigDecimal totalCost = BigDecimal.ZERO;

        for (RecipeIngredient ri : recipeIngredients) {
            BigDecimal cost =
                    ri.getIngredient().getCostPerUnit()
                            .multiply(BigDecimal.valueOf(ri.getQuantityRequired()));
            totalCost = totalCost.add(cost);
        }

        BigDecimal sellingPrice = menuItem.getSellingPrice();
        double profitMargin =
                sellingPrice.subtract(totalCost)
                        .divide(sellingPrice, 2, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profitMargin);

        return recordRepository.save(record);
    }

    @Override
    public ProfitCalculationRecord getById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profit record not found"));
    }

    @Override
    public List<ProfitCalculationRecord> getByMenuItem(Long menuItemId) {
        return recordRepository.findByMenuItemId(menuItemId);
    }

    @Override
    public List<ProfitCalculationRecord> getAll() {
        return recordRepository.findAll();
    }
}
