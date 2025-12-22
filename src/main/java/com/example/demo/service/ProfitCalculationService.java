public interface ProfitCalculationService {

    ProfitCalculation calculateProfit(Long menuItemId);

    ProfitCalculation getCalculationById(Long id);

    List<ProfitCalculation> getCalculationsForMenuItem(Long menuItemId);

    List<ProfitCalculation> getAllCalculations();
}
