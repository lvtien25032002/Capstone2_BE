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
    long calories;
    long protein;
    long fat;
    long carbs;
    List<MealResponse> meals;

}
