package cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.IngredientForRecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.MealType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeResponse {
    String recipe_ID;
    String recipeName;
    String description;
    String cookingInstructions;
    String imageURL;
    Double totalCalories;
    Double totalProtein;
    Double totalCarbs;
    Double totalFat;
    Set<MealType> mealType;
    List<IngredientForRecipeResponse> ingredientList;
}
