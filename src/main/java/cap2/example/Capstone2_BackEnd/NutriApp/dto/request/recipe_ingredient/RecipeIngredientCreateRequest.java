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
public class RecipeIngredientCreateRequest {

    String Recipe_Ingredient_ID;

    String Recipe_ID;

    String Ingredient_ID;

    int Quantity;
    String unit;

}
