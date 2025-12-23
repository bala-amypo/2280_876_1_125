package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculation;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.ProfitCalculationRepository;
import com.example.demo.service.ProfitCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuItemRepository;
    private final ProfitCalculationRepository profitCalculationRepository;

    @Override
    public ProfitCalculation calculateProfit(Long menuItemId) {

        // 1️⃣ Get MenuItem
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));

        // 2️⃣ Selling price (BigDecimal)
        BigDecimal sellingPrice = menuItem.getSellingPrice();

        // 3️⃣ Calculate total ingredient cost
        BigDecimal totalCost = calculateTotalCost(menuItem);

        // 4️⃣ ✅ PROFIT CALCULATION (THIS IS WHERE YOUR CODE GOES)
        BigDecimal profit = sellingPrice.subtract(totalCost);

        // 5️⃣ Save result
        ProfitCalculation calculation = new ProfitCalculation();
        calculation.setMenuItem(menuItem);
        calculation.setTotalCost(totalCost);
        calculation.setSellingPrice(sellingPrice);
        calculation.setProfit(profit);

        return profitCalculationRepository.save(calculation);
    }

    // 🔹 Helper method
    private BigDecimal calculateTotalCost(MenuItem menuItem) {
        // Your ingredient cost logic here
        return BigDecimal.ZERO; // replace with real logic
    }
}
