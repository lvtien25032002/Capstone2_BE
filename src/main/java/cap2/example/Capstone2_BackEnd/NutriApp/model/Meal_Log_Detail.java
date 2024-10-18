package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
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
public class Meal_Log_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID Meal_Log_Detail_ID;

    @ManyToOne
    @JoinColumn(name = "Meal_Log_ID")
    Meal_Log Meal_Log_ID;

    @ManyToOne
    @JoinColumn(name = "Recipe_ID")
    Recipe Recipe_ID;
    Double calories;
    Double protein;
    Double fat;
    Double carbs;

}
