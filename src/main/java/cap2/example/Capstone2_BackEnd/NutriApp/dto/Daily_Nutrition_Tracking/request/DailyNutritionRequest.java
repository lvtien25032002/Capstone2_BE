package cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.request;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.MealRequest;
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
public class DailyNutritionRequest {
    @NotEmpty(message = "USER_ID_REQUIRED")
    String User_ID;

    @NotNull(message = "MEALS_REQUIRED")
    List<MealRequest> meals;
    @NotNull(message = "DATE_REQUIRED")
    LocalDate date;
}
