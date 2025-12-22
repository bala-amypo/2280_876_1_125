 package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuRepo;
    private final RecipeIngredientRepository recipeRepo;
    private final ProfitCalculationRepository profitRepo;

    public ProfitCalculationServiceImpl(
            MenuItemRepository menuRepo,
            RecipeIngredientRepository recipeRepo,
            ProfitCalculationRepository profitRepo) {
        this.menuRepo = menuRepo;
        this.recipeRepo = recipeRepo;
        this.profitRepo = profitRepo;
    }

    @Override
    public ProfitCalculation calculateProfit(Long menuItemId) {
        MenuItem menuItem = menuRepo.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));

        double cost = recipeRepo.findByMenuItemId(menuItemId)
                .stream()
                .mapToDouble(r ->
                        r.getQuantity() * r.getIngredient().getCostPerUnit())
                .sum();

        double profit = menuItem.getSellingPrice() - cost;

        ProfitCalculation pc = new ProfitCalculation();
        pc.setMenuItem(menuItem);
        pc.setTotalCost(cost);
        pc.setProfit(profit);

        return profitRepo.save(pc);
    }

    @Override
    public ProfitCalculation getCalculationById(Long id) {
        return profitRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculation not found"));
    }

    @Override
    public List<ProfitCalculation> getCalculationsForMenuItem(Long menuItemId) {
        return profitRepo.findByMenuItemId(menuItemId);
    }

    @Override
    public List<ProfitCalculation> getAllCalculations() {
        return profitRepo.findAll();
    }
}
