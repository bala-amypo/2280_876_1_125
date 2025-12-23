package com.example.demo.service;

import com.example.demo.entity.ProfitCalculationRecord;
import java.util.List;

public interface ProfitCalculationService {
    ProfitCalculationRecord save(ProfitCalculationRecord record);
    List<ProfitCalculationRecord> findAll();
}
