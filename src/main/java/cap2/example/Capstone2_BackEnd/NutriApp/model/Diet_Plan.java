package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Diet_Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Diet_Plan_ID;
    private String dietPlanName;
    private String description;
    private DietPlanType dietPlanType;
    private Double calorieRangeMin;
    private Double calorieRangeMax;
    private Double proteinRangeMin;
    private Double proteinRangeMax;
    private Double fatRangeMin;
    private Double fatRangeMax;
    private Double carbsRangeMin;
    private Double carbsRangeMax;
    public enum DietPlanType {
        BALANCED,
        LOW_CARB,
        HIGH_PROTEIN,
        KETO,
        VEGAN
    }


}
