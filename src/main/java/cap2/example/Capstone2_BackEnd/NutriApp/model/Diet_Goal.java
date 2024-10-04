package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Diet_Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Diet_Goal_ID;
    private Long User_ID;
    private double calorieTarget;
    private double proteinTarget;
    private double fatTarget;
    private double carbTarget;
    private int mealFrequency;
    private Timestamp createdDate;

    public Diet_Goal() {
    }

    public Diet_Goal(UUID diet_Goal_ID, Long user_ID, double calorieTarget, double proteinTarget, double fatTarget, double carbTarget, int mealFrequency, Timestamp createdDate) {
        Diet_Goal_ID = diet_Goal_ID;
        User_ID = user_ID;
        this.calorieTarget = calorieTarget;
        this.proteinTarget = proteinTarget;
        this.fatTarget = fatTarget;
        this.carbTarget = carbTarget;
        this.mealFrequency = mealFrequency;
        this.createdDate = createdDate;
    }

    public UUID getDiet_Goal_ID() {
        return Diet_Goal_ID;
    }

    public void setDiet_Goal_ID(UUID diet_Goal_ID) {
        Diet_Goal_ID = diet_Goal_ID;
    }

    public Long getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(Long user_ID) {
        User_ID = user_ID;
    }

    public double getCalorieTarget() {
        return calorieTarget;
    }

    public void setCalorieTarget(double calorieTarget) {
        this.calorieTarget = calorieTarget;
    }

    public double getProteinTarget() {
        return proteinTarget;
    }

    public void setProteinTarget(double proteinTarget) {
        this.proteinTarget = proteinTarget;
    }

    public double getFatTarget() {
        return fatTarget;
    }

    public void setFatTarget(double fatTarget) {
        this.fatTarget = fatTarget;
    }

    public double getCarbTarget() {
        return carbTarget;
    }

    public void setCarbTarget(double carbTarget) {
        this.carbTarget = carbTarget;
    }

    public int getMealFrequency() {
        return mealFrequency;
    }

    public void setMealFrequency(int mealFrequency) {
        this.mealFrequency = mealFrequency;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
