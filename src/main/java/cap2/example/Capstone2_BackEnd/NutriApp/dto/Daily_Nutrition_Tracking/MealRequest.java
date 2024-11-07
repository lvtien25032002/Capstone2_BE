package cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking;

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
    String mealType;
    List<String> dishNames;
}
