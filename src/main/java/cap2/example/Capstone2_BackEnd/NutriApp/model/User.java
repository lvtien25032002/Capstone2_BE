package cap2.example.Capstone2_BackEnd.NutriApp.model;


import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.ActivityFactor;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.DietType;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.NutritionPlan;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class User extends BaseUser {
    int age;
    // True = Male ; False = Female
    boolean gender;
    double weight;
    double height;
    ActivityFactor activityFactor;
    NutritionPlan nutritionPlan;
    DietType dietType;
}
