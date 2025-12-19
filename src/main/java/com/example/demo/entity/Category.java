@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;
    private Boolean active = true;

    @ManyToMany(mappedBy = "categories")
    private List<MenuItem> menuItems = new ArrayList<>();
}
