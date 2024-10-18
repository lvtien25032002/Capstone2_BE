package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Daily_Nutrition_Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Daily_Nutrition_Tracking_ID;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    User User_ID;
    Date date;
    double totalCalories;
    double totalCarbs;
    double totalProtein;
    double totalFat;


}
