package cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.ingredient.IngredientResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.RecipeResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeIngredientResponse {
    String Recipe_Ingredient_ID;
    RecipeResponse Recipe_ID;
    IngredientResponse Ingredient_ID;
    int quantity;
    String unit;
}
