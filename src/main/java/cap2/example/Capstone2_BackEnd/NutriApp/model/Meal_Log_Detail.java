package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
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
public class Meal_Log_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Meal_Log_Detail_ID;

    @ManyToOne
    @JoinColumn(name = "Meal_Log_ID")
    private Meal_Log Meal_Log_ID;

    @ManyToOne
    @JoinColumn(name = "Recipe_ID")
    private Recipe Recipe_ID;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbs;
    
}
