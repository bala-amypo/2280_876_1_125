package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculation;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.ProfitCalculationRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.ProfitCalculationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuRepo;
    private final RecipeIngredientRepository recipeRepo;
    private final ProfitCalculationRepository profitRepo;

    @Override
    public ProfitCalculation calculateProfit(Long menuItemId) {
        MenuItem menuItem = menuRepo.findById(menuItemId).orElseThrow();

        List<RecipeIngredient> ingredients = recipeRepo.findByMenuItemId(menuItemId);

        double totalCost = ingredients.stream()
                .mapToDouble(i -> i.getIngredient().getCost() * i.getQuantity())
                .sum();

        double profit = menuItem.getSellingPrice() - totalCost;

        ProfitCalculation calc = new ProfitCalculation();
        calc.setMenuItem(menuItem);
        calc.setCost(totalCost);
        calc.setProfit(profit);

        return profitRepo.save(calc);
    }

    @Override
    public ProfitCalculation getCalculationById(Long id) {
        return profitRepo.findById(id).orElseThrow();
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
