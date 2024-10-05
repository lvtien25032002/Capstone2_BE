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
public class Meal_Log {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Meal_Log_ID;
    @ManyToOne
    @JoinColumn(name = "User_ID")
    private User User_ID;
    @Enumerated(EnumType.STRING)
    private MealType mealType;
    private Date date;
    public enum MealType {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }


}

