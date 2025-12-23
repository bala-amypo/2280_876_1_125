package com.example.demo.service.impl;

import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.service.ProfitCalculationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final ProfitCalculationRecordRepository repository;

    public ProfitCalculationServiceImpl(ProfitCalculationRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProfitCalculationRecord save(ProfitCalculationRecord record) {
        return repository.save(record);
    }

    @Override
    public List<ProfitCalculationRecord> findAll() {
        return repository.findAll();
    }
}
