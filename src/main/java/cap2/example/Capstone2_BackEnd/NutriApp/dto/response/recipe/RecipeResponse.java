package cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ImageResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe_ingredient.IngredientForRecipeResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
    List<IngredientForRecipeResponse> ingredientList;
}
