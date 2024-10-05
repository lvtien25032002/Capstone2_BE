package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Daily_Nutrition_Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Daily_Nutrition_Tracking_ID;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private User User_ID;
    private Date date;
    private double totalCalories;
    private double totalCarbs;
    private double totalProtein;
    private double totalFat;

    
}
