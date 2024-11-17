package cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeForDailyTrackingResponse {
    String recipeID;
    String recipeName;
    String imageURL;
    int calories;
    int protein;
    int carbs;
    int fat;
}