package cap2.example.Capstone2_BackEnd.NutriApp.model;


import cap2.example.Capstone2_BackEnd.NutriApp.enums.MealType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Daily_Nutrition_Tracking_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String Daily_Nutrition_Tracking_Detail_ID;

    @ManyToOne
    Daily_Nutrition_Tracking daily_Nutrition_Tracking_ID;

    @ManyToOne
    Recipe recipe_ID;

    MealType mealType;


}
