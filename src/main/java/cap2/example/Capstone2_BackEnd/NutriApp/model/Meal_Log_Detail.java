package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
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

    public Meal_Log_Detail() {
    }

    public Meal_Log_Detail(UUID meal_Log_Detail_ID, Meal_Log meal_Log_ID, Recipe recipe_ID, Double calories, Double protein, Double fat, Double carbs) {
        Meal_Log_Detail_ID = meal_Log_Detail_ID;
        Meal_Log_ID = meal_Log_ID;
        Recipe_ID = recipe_ID;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public UUID getMeal_Log_Detail_ID() {
        return Meal_Log_Detail_ID;
    }

    public void setMeal_Log_Detail_ID(UUID meal_Log_Detail_ID) {
        Meal_Log_Detail_ID = meal_Log_Detail_ID;
    }

    public Meal_Log getMeal_Log_ID() {
        return Meal_Log_ID;
    }

    public void setMeal_Log_ID(Meal_Log meal_Log_ID) {
        Meal_Log_ID = meal_Log_ID;
    }

    public Recipe getRecipe_ID() {
        return Recipe_ID;
    }

    public void setRecipe_ID(Recipe recipe_ID) {
        Recipe_ID = recipe_ID;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }
}
