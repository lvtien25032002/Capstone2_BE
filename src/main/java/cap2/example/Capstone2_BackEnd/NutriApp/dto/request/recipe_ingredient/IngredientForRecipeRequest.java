package cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe_ingredient;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientForRecipeRequest {
    String ingredientId;
    int quantity;
}
