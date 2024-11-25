package cap2.example.Capstone2_BackEnd.NutriApp.dto.nutritionalCalculation;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NutritionalCalculationResponse {
    int caloriesNeeded;
    int proteinNeeded;
    int fatNeeded;
    int carbsNeeded;
}
