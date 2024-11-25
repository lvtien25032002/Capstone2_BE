package cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.response;

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
public class MealResponseForNutritionPlan {
    String mealType;
    List<SimpleRecipeResponse> recipeList;
}