package com.example.demo.repository;

import com.example.demo.entity.ProfitCalculationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfitCalculationRecordRepository extends JpaRepository<ProfitCalculationRecord, Long> {
}
