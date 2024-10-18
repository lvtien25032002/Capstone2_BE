package cap2.example.Capstone2_BackEnd.NutriApp.dto.request.ingredient;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientUpdateRequest {
    String ingredientName;
    String ingredientType;
    String ingredientDescription;
    double calories;
    double protein;
    double fat;
    double carbs;


}
