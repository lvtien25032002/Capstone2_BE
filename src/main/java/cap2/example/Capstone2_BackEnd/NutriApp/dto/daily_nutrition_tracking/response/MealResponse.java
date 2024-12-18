package cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.response;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.response.SimpleRecipeResponse;
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
public class MealResponse {
    String DailyNutritionTrackingID;
    String mealType;
    List<SimpleRecipeResponse> recipeList;
}