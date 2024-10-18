package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Diet_Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID Diet_Plan_ID;
    String dietPlanName;
    String description;
    DietPlanType dietPlanType;
    Double calorieRangeMin;
    Double calorieRangeMax;
    Double proteinRangeMin;
    Double proteinRangeMax;
    Double fatRangeMin;
    Double fatRangeMax;
    Double carbsRangeMin;
    Double carbsRangeMax;

    public enum DietPlanType {
        BALANCED,
        LOW_CARB,
        HIGH_PROTEIN,
        KETO,
        VEGAN
    }


}
