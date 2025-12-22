public interface RecipeIngredientRepository
        extends JpaRepository<RecipeIngredient, Long> {

    List<RecipeIngredient> findByMenuItemId(Long menuItemId);

    @Query("SELECT SUM(r.quantity) FROM RecipeIngredient r WHERE r.ingredient.id = :ingredientId")
    Double sumQuantityByIngredientId(Long ingredientId);
}
