public interface IngredientService {

    Ingredient createIngredient(Ingredient ingredient);

    Ingredient updateIngredient(Long id, Ingredient ingredient);

    Ingredient getIngredientById(Long id);

    List<Ingredient> getAllIngredients();

    void deactivateIngredient(Long id);
}
