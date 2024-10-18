package cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeResponse {
    String recipe_ID;
    String recipeName;
    String description;
    String cookingInstructions;
    Double totalCalories;
    Double totalProtein;
    Double totalCarbs;
    Double totalFat;
}
