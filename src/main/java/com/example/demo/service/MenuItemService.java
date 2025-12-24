ProfitCalculationRecord calculateProfit(Long menuItemId);
ProfitCalculationRecord getCalculationById(Long id);
List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId);
List<ProfitCalculationRecord> getAllCalculations();
List<ProfitCalculationRecord> findRecordsWithMarginBetween(Double min, Double max);
List<ProfitCalculationRecord> getByMenuItem(Long menuItemId);
