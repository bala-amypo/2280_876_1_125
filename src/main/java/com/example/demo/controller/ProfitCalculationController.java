package com.example.demo.controller;

import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.service.ProfitCalculationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profits")
public class ProfitCalculationController {

    private final ProfitCalculationService service;

    public ProfitCalculationController(ProfitCalculationService service) {
        this.service = service;
    }

    @PostMapping("/{menuItemId}")
    public ProfitCalculationRecord calculate(@PathVariable Long menuItemId) {
        return service.calculateProfit(menuItemId);
    }

    @GetMapping("/menu/{menuItemId}")
    public List<ProfitCalculationRecord> getByMenu(@PathVariable Long menuItemId) {
        return service.getByMenuItem(menuItemId);
    }
}
