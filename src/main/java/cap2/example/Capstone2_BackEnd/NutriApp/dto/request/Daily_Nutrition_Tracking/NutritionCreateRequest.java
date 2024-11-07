package cap2.example.Capstone2_BackEnd.NutriApp.dto.request.Daily_Nutrition_Tracking;


import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NutritionCreateRequest {

    @NotNull(message = "Date cannot be null")
    @Temporal(TemporalType.DATE)
    LocalDate date;

    @NotNull(message = " INGREDIENT_CALORIES_REQUIRED")
    double totalCalories;

    @NotNull(message = "INGREDIENT_CARBS_REQUIRED")
    double totalCarbs;

    @NotNull(message = "INGREDIENT_PROTEIN_REQUIRED")
    double totalProtein;

    @NotNull(message = " INGREDIENT_FAT_REQUIRED")
    double totalFat;
}
