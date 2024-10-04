package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Ingredient_ID;
    private String ingredientName;
    private String ingredientType;
    private String ingredientDescription;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;

    public Ingredient() {
    }

    public Ingredient(UUID ingredient_ID, String ingredientName, String ingredientType, String ingredientDescription, double calories, double protein, double fat, double carbs) {
        Ingredient_ID = ingredient_ID;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.ingredientDescription = ingredientDescription;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public UUID getIngredient_ID() {
        return Ingredient_ID;
    }

    public void setIngredient_ID(UUID ingredient_ID) {
        Ingredient_ID = ingredient_ID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public void setIngredientDescription(String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }
}
