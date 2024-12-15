package cap2.example.Capstone2_BackEnd.NutriApp.dto.user;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NutritionPlanResponse {
    String nutritionPlanName;
    String description;
    String imageURL;
    int proteinPercentage;
    String proteinDescription = "Protein is essential for your overall health. It will help maintain and repair muscle tissue.";
    int fatPercentage;
    String fatDescription = "No all fats are bad. Healthy fats can even help increase health and energy.";
    int carbsPercentage;
    String carbsDescription = "Carbs give you energy. They are part of a healthy diet and also help with maintaining your weight.";
}
