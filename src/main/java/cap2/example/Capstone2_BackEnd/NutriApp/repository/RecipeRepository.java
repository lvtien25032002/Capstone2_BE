package cap2.example.Capstone2_BackEnd.NutriApp.repository;

import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe_Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecipeRepository extends JpaRepository<Recipe, String> {
    boolean existsByRecipeName(String recipeName);

    Recipe findRecipeByRecipeIngredients(Recipe_Ingredient recipeIngredients);

    Recipe findByRecipeName(String recipeName);

    @Query("SELECT r FROM Recipe r WHERE (:minCalories IS NULL OR r.totalCalories >= :minCalories) " +
            "AND (:maxCalories IS NULL OR r.totalCalories <= :maxCalories)")
    List<Recipe> findByCaloriesRange(@Param("minCalories") Double minCalories, @Param("maxCalories") Double maxCalories);

    @Query("SELECT r FROM Recipe r WHERE (:minProtein IS NULL OR r.totalProtein >= :minProtein) " +
            "AND (:maxProtein IS NULL OR r.totalProtein <= :maxProtein)")
    List<Recipe> findByProteinRange(@Param("minProtein") Double minProtein, @Param("maxProtein") Double maxProtein);

    @Query("SELECT r FROM Recipe r WHERE (:minCarbs IS NULL OR r.totalCarbs >= :minCarbs) " +
            "AND (:maxCarbs IS NULL OR r.totalCarbs <= :maxCarbs)")
    List<Recipe> findByCarbsRange(@Param("minCarbs") Double minCarbs, @Param("maxCarbs") Double maxCarbs);

    @Query("SELECT r FROM Recipe r WHERE (:minFat IS NULL OR r.totalFat >= :minFat) " +
            "AND (:maxFat IS NULL OR r.totalFat <= :maxFat)")
    List<Recipe> findByFatRange(@Param("minFat") Double minFat, @Param("maxFat") Double maxFat);
}
