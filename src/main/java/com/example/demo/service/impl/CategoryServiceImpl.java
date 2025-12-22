@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    public Category createCategory(Category category) {
        category.setActive(true);
        return repo.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        Category db = repo.findById(id).orElseThrow();
        db.setName(category.getName());
        return repo.save(db);
    }

    public Category getCategoryById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<Category> getAllCategories() {
        return repo.findAll();
    }

    public void deactivateCategory(Long id) {
        Category c = repo.findById(id).orElseThrow();
        c.setActive(false);
        repo.save(c);
    }
}
