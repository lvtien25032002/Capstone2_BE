package cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.response;

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
public class DailyNutritionResponse {
    String User_ID;
    List<RecipeForDailyTrackingResponse> meals;
    LocalDate date;
}
