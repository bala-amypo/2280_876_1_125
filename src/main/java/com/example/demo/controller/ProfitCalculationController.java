package com.example.demo.controller;

import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.service.ProfitCalculationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profit-records")
public class ProfitCalculationController {

    private final ProfitCalculationService service;

    public ProfitCalculationController(ProfitCalculationService service) {
        this.service = service;
    }

    @PostMapping
    public ProfitCalculationRecord save(@RequestBody ProfitCalculationRecord record) {
        return service.save(record);
    }

    @GetMapping
    public List<ProfitCalculationRecord> findAll() {
        return service.findAll();
    }
}
