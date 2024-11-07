package cap2.example.Capstone2_BackEnd.NutriApp.dto.request.Daily_Nutrition_Tracking;

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
    String User_ID;
    List<MealRequest> meals;
    LocalDate date;
}
