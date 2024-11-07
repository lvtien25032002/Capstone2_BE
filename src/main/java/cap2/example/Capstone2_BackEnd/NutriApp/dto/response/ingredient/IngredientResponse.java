package cap2.example.Capstone2_BackEnd.NutriApp.dto.response.ingredient;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientResponse {
    String Ingredient_ID;
    String ingredientName;
    String ingredientType;
    String ingredientDescription;
    String imageURL;
    double calories;
    double protein;
    double fat;
    double carbs;

}