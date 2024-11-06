package cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.IngredientForRecipeRequest;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "RECIPE_NAME_REQUIRED")
    String recipeName;
    String description;
    @NotEmpty(message = "COOKING_INSTRUCTIONS_REQUIRED")
    String cookingInstructions;
    @NotEmpty(message = "IMAGE_URL_REQUIRED")
    String imageURL;

    @NotEmpty(message = "MEAL_TYPE_REQUIRED")
    Set<String> mealType;
    @NotEmpty(message = "INGREDIENT_LIST_REQUIRED")
    List<IngredientForRecipeRequest> ingredientList;
}
