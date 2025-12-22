public interface RecipeIngredientService {

    RecipeIngredient addIngredientToRecipe(
            Long menuItemId, Long ingredientId, Double quantity);

    RecipeIngredient updateRecipeIngredient(Long id, Double quantity);

    List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId);

    void removeIngredientFromRecipe(Long id);

    Double getTotalQuantityOfIngredient(Long ingredientId);
}
