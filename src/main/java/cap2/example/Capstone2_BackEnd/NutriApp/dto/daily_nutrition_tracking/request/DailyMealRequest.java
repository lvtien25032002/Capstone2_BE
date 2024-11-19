package cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyMealRequest {
    @NotEmpty(message = "USER_ID_REQUIRED")
    String User_ID;
    @NotNull(message = "RECIPE_LIST_REQUIRED")
    List<String> recipeList;
    @NotNull(message = "MEAL_TYPE_REQUIRED")
    String mealType;
    @NotNull(message = "DATE_REQUIRED")
    LocalDate date;
}
