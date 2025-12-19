@Entity
public class ProfitCalculationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MenuItem menuItem;

    private BigDecimal totalCost;
    private BigDecimal profitMargin;

    private Timestamp calculatedAt;

    @PrePersist
    void create() {
        calculatedAt = new Timestamp(System.currentTimeMillis());
    }
}
