package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.ProfitCalculationRecord;

public interface ProfitCalculationService {

    ProfitCalculationRecord getCalculationById(Long id);

    List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId);

    List<ProfitCalculationRecord> getAllCalculations();

    ProfitCalculationRecord calculateProfit(Long menuItemId);
}
