@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repo;

    public IngredientServiceImpl(IngredientRepository repo) {
        this.repo = repo;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        ingredient.setActive(true);
        return repo.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        Ingredient db = repo.findById(id).orElseThrow();
        db.setName(ingredient.getName());
        db.setCost(ingredient.getCost());
        return repo.save(db);
    }

    public Ingredient getIngredientById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<Ingredient> getAllIngredients() {
        return repo.findAll();
    }

    public void deactivateIngredient(Long id) {
        Ingredient i = repo.findById(id).orElseThrow();
        i.setActive(false);
        repo.save(i);
    }
}
