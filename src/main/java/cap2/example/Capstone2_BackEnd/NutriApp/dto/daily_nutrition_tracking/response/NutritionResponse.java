package cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NutritionResponse {
    String Daily_Nutrition_Tracking_ID;
    String user_ID;
    LocalDate date;
    String MealType;
    double calories;
    double protein;
    double fat;
    double carbs;
}
