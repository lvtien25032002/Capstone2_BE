package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    double totalCalories;
    double totalCarbs;
    double totalProtein;
    double totalFat;


}
