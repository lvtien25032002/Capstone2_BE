package cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.response;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.MealResponse;
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
    double calories;
    double protein;
    double fat;
    double carbs;
    List<MealResponse> meals;

}
