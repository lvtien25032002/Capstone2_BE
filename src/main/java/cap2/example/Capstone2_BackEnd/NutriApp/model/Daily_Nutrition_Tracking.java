package cap2.example.Capstone2_BackEnd.NutriApp.model;


import cap2.example.Capstone2_BackEnd.NutriApp.enums.recipe.MealType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Daily_Nutrition_Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String Daily_Nutrition_Tracking_ID;
    @ManyToOne
    User User;
    LocalDate date;
    double calories;
    double carbs;
    double protein;
    double fat;
    MealType mealType;
    @OneToMany(mappedBy = "daily_Nutrition_Tracking_ID", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Daily_Nutrition_Tracking_Detail> daily_Nutrition_Tracking_Details;
}
