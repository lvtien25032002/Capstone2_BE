package cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.ingredient;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientCreateRequest {

    @Size(min = 2, max = 50, message = "INGREDIENT_NAME_INVALID")
    @NotEmpty(message = "INGREDIENT_NAME_REQUIRED")
    String ingredientName;

    @NotEmpty(message = "INGREDIENT_TYPE_REQUIRED")
    String ingredientType;

    String ingredientDescription;
    String unit;
    String imageURL;


    @NotNull(message = " INGREDIENT_CALORIES_REQUIRED")
    double calories;
    @NotNull(message = "INGREDIENT_PROTEIN_REQUIRED")
    double protein;
    @NotNull(message = " INGREDIENT_FAT_REQUIRED")
    double fat;
    @NotNull(message = "INGREDIENT_CARBS_REQUIRED")
    double carbs;


}
