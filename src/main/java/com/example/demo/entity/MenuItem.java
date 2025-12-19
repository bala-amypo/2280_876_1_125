@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;
    private BigDecimal sellingPrice;
    private Boolean active = true;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @PrePersist
    void create() {
        createdAt = updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    void update() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
