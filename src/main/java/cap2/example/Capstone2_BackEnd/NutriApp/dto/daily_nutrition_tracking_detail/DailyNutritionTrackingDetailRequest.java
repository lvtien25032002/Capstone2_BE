package cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking_detail;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyNutritionTrackingDetailRequest {
    String daily_Nutrition_Tracking_ID;
    String recipe_ID;
}
