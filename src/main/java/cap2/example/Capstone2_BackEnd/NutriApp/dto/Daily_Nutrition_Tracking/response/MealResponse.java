package cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.response;

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
    String mealType;
    List<RecipeForDailyTrackingResponse> recipeList;
}