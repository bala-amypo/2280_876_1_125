package com.example.demo.controller;

import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.service.ProfitCalculationServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profit")
public class ProfitCalculationController {

    private final ProfitCalculationServiceImpl profitService;

    public ProfitCalculationController(ProfitCalculationServiceImpl profitService) {
        this.profitService = profitService;
    }

    @PostMapping("/{menuItemId}")
    public ProfitCalculationRecord calculateProfit(
            @PathVariable Long menuItemId) {

        return profitService.calculateProfit(menuItemId);
    }
}
