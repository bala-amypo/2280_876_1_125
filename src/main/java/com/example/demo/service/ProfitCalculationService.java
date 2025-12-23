package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.ProfitCalculationRecord;

public interface ProfitCalculationService {

    ProfitCalculationRecord calculateProfit(Long menuItemId);

    List<ProfitCalculationRecord> getAllProfitRecords();
}
