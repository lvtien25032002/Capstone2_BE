package cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.request;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.IngredientForRecipeRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class RecipeRequest {
    String recipeName;
    String description;
    String cookingInstructions;
    String imageURL;
    Double prepTime;
    Double cookTime;
    String difficultyLevel;
    Set<String> nutritionalQuality;
    Set<String> mealType;
    List<IngredientForRecipeRequest> ingredientList;
}
