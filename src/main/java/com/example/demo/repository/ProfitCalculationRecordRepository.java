public interface ProfitCalculationRepository
        extends JpaRepository<ProfitCalculation, Long> {

    List<ProfitCalculation> findByMenuItemId(Long menuItemId);
}
