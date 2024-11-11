package cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientRequest {
    String ingredientName;
    String ingredientType;
    String ingredientDescription;
    String unit;
    String imageURL;
    double calories;
    double protein;
    double fat;
    double carbs;


}
