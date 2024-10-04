package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Meal_Log {

    public enum MealType {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Meal_Log_ID;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private User User_ID;

    @Enumerated(EnumType.STRING)
    private MealType mealType;
    private Date date;

    public Meal_Log() {
    }

    public Meal_Log(UUID meal_Log_ID, User user_ID, MealType mealType, Date date) {
        Meal_Log_ID = meal_Log_ID;
        User_ID = user_ID;
        this.mealType = mealType;
        this.date = date;
    }

    public UUID getMeal_Log_ID() {
        return Meal_Log_ID;
    }

    public void setMeal_Log_ID(UUID meal_Log_ID) {
        Meal_Log_ID = meal_Log_ID;
    }

    public User getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(User user_ID) {
        User_ID = user_ID;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

