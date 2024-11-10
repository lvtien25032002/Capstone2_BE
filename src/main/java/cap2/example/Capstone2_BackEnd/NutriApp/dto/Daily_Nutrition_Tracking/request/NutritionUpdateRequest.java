package cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NutritionUpdateRequest {
    String user_ID;
    LocalDate date;
    List<MealForUpdate> meals;

    // Getters và Setters

    public static class MealForUpdate {
        private String mealType;
        private List<String> dishNames;

        public List<String> getDishNames() {
            return dishNames;
        }

        public void setDishNames(List<String> dishNames) {
            this.dishNames = dishNames;
        }

        public String getMealType() {
            return mealType;
        }

        public void setMealType(String mealType) {
            this.mealType = mealType;
        }
    }
}
