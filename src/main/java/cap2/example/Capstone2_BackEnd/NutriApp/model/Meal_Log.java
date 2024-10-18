package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Meal_Log {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String Meal_Log_ID;
    @ManyToOne
    @JoinColumn(name = "User_ID")
    User User_ID;
    @Enumerated(EnumType.STRING)
    MealType mealType;
    Date date;

    public enum MealType {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }


}

