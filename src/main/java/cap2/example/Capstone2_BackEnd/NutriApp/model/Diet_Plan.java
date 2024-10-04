package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Diet_Plan {
    public enum DietPlanType {
        BALANCED,
        LOW_CARB,
        HIGH_PROTEIN,
        KETO,
        VEGAN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Diet_Plan_ID;
    private String dietPlanName;
    private String description;
    private DietPlanType dietPlanType;
    private Double calorieRangeMin;
    private Double calorieRangeMax;
    private Double proteinRangeMin;
    private Double proteinRangeMax;
    private Double fatRangeMin;
    private Double fatRangeMax;
    private Double carbsRangeMin;
    private Double carbsRangeMax;

    public Diet_Plan() {
    }

    public Diet_Plan(UUID diet_Plan_ID, String dietPlanName, String description, DietPlanType dietPlanType, Double calorieRangeMin, Double calorieRangeMax, Double proteinRangeMin, Double proteinRangeMax, Double fatRangeMin, Double fatRangeMax, Double carbsRangeMin, Double carbsRangeMax) {
        Diet_Plan_ID = diet_Plan_ID;
        this.dietPlanName = dietPlanName;
        this.description = description;
        this.dietPlanType = dietPlanType;
        this.calorieRangeMin = calorieRangeMin;
        this.calorieRangeMax = calorieRangeMax;
        this.proteinRangeMin = proteinRangeMin;
        this.proteinRangeMax = proteinRangeMax;
        this.fatRangeMin = fatRangeMin;
        this.fatRangeMax = fatRangeMax;
        this.carbsRangeMin = carbsRangeMin;
        this.carbsRangeMax = carbsRangeMax;
    }

    public UUID getDiet_Plan_ID() {
        return Diet_Plan_ID;
    }

    public void setDiet_Plan_ID(UUID diet_Plan_ID) {
        Diet_Plan_ID = diet_Plan_ID;
    }

    public String getDietPlanName() {
        return dietPlanName;
    }

    public void setDietPlanName(String dietPlanName) {
        this.dietPlanName = dietPlanName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DietPlanType getDietPlanType() {
        return dietPlanType;
    }

    public void setDietPlanType(DietPlanType dietPlanType) {
        this.dietPlanType = dietPlanType;
    }

    public Double getCalorieRangeMin() {
        return calorieRangeMin;
    }

    public void setCalorieRangeMin(Double calorieRangeMin) {
        this.calorieRangeMin = calorieRangeMin;
    }

    public Double getCalorieRangeMax() {
        return calorieRangeMax;
    }

    public void setCalorieRangeMax(Double calorieRangeMax) {
        this.calorieRangeMax = calorieRangeMax;
    }

    public Double getProteinRangeMin() {
        return proteinRangeMin;
    }

    public void setProteinRangeMin(Double proteinRangeMin) {
        this.proteinRangeMin = proteinRangeMin;
    }

    public Double getProteinRangeMax() {
        return proteinRangeMax;
    }

    public void setProteinRangeMax(Double proteinRangeMax) {
        this.proteinRangeMax = proteinRangeMax;
    }

    public Double getFatRangeMin() {
        return fatRangeMin;
    }

    public void setFatRangeMin(Double fatRangeMin) {
        this.fatRangeMin = fatRangeMin;
    }

    public Double getFatRangeMax() {
        return fatRangeMax;
    }

    public void setFatRangeMax(Double fatRangeMax) {
        this.fatRangeMax = fatRangeMax;
    }

    public Double getCarbsRangeMin() {
        return carbsRangeMin;
    }

    public void setCarbsRangeMin(Double carbsRangeMin) {
        this.carbsRangeMin = carbsRangeMin;
    }

    public Double getCarbsRangeMax() {
        return carbsRangeMax;
    }

    public void setCarbsRangeMax(Double carbsRangeMax) {
        this.carbsRangeMax = carbsRangeMax;
    }
}
