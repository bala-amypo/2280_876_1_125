package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.ProfitCalculation;

public interface ProfitCalculationService {

    ProfitCalculation calculateProfit(Long menuItemId);

    ProfitCalculation getCalculationById(Long id);

    List<ProfitCalculation> getCalculationsForMenuItem(Long menuItemId);

    List<ProfitCalculation> getAllCalculations();
}
