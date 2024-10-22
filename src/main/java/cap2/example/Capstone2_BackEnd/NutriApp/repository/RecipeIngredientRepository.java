package cap2.example.Capstone2_BackEnd.NutriApp.repository;


import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe_Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<Recipe_Ingredient, String> {
    List<Recipe_Ingredient> findRecipe_IngredientByRecipe(Recipe recipe);
}
