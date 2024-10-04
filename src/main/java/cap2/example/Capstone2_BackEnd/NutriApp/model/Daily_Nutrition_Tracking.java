package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
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

    public Daily_Nutrition_Tracking() {
    }

    public Daily_Nutrition_Tracking(UUID daily_Nutrition_Tracking_ID, User user_ID, Date date, double totalCalories, double totalCarbs, double totalProtein, double totalFat) {
        Daily_Nutrition_Tracking_ID = daily_Nutrition_Tracking_ID;
        User_ID = user_ID;
        this.date = date;
        this.totalCalories = totalCalories;
        this.totalCarbs = totalCarbs;
        this.totalProtein = totalProtein;
        this.totalFat = totalFat;
    }

    public UUID getDaily_Nutrition_Tracking_ID() {
        return Daily_Nutrition_Tracking_ID;
    }

    public void setDaily_Nutrition_Tracking_ID(UUID daily_Nutrition_Tracking_ID) {
        Daily_Nutrition_Tracking_ID = daily_Nutrition_Tracking_ID;
    }

    public User getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(User user_ID) {
        User_ID = user_ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }
}
