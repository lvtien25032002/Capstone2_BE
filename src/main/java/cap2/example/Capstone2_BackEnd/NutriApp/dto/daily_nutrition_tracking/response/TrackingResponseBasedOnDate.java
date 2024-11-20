package cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.response;

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
public class TrackingResponseBasedOnDate {
    LocalDate date;
    long totalCalories;
    long totalProtein;
    long totalFat;
    long totalCarbs;
    List<MealResponse> meals;

}
