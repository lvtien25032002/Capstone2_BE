package cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe_ingredient.IngredientForRecipeRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class RecipeCreateRequest {
    @NotEmpty(message = "RECIPE_NAME_REQUIRED")
    String recipeName;
    String description;
    @NotEmpty(message = "COOKING_INSTRUCTIONS_REQUIRED")
    String cookingInstructions;
    String imageURL;

    List<IngredientForRecipeRequest> ingredientList;
}
