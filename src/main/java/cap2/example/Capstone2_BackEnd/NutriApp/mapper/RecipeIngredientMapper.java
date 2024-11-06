package cap2.example.Capstone2_BackEnd.NutriApp.mapper;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.IngredientForRecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.RecipeIngredientResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe_Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeIngredientMapper {
    Recipe_Ingredient toRecipeIngredient(Recipe recipe);

    RecipeIngredientResponse toRecipeIngredientResponse(Recipe_Ingredient recipe_ingredient);

    IngredientForRecipeResponse toIngredientResponseforRecipe(Recipe_Ingredient recipe_ingredient);

}
