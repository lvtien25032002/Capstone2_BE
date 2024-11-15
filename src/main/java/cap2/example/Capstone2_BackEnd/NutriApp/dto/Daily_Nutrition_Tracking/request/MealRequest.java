package cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.request;

import jakarta.validation.constraints.NotNull;
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
public class MealRequest {
    @NotNull(message = "MEAL_TYPE_REQUIRED")
    String mealType;
    @NotNull(message = "RECIPE_LIST_REQUIRED")
    List<String> recipeList;
}
