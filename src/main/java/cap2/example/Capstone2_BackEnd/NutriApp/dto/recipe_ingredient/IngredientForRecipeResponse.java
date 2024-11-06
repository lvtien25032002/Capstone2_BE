package cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientForRecipeResponse {
    String ingredientName;
    int quantity;
    String unit;
}
