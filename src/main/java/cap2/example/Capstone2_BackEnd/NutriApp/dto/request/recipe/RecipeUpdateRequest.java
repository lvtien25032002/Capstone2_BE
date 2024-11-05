package cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe_ingredient.IngredientForRecipeRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeUpdateRequest {
    @NotEmpty(message = "RECIPE_NAME_REQUIRED")
    String recipeName;

    String description;
    @NotEmpty(message = "RECIPE_TOTAL_CALORIES_REQUIRED")
    String cookingInstructions;
    String imageURL;
    List<IngredientForRecipeRequest> ingredientList;
}
