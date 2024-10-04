package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID User_ID;

    private String username;
    private String password;
    private String email;
    private String fullname;
    private int age;
    private boolean gender;
    private double weight;
    private double height;
    private String goal;
    private LocalDateTime createdAt;

    public User() {
    }

    public User(UUID user_ID, String username, String password, String email, String fullname, int age, boolean gender, double weight, double height, String goal) {
        User_ID = user_ID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
    }

    public UUID getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(UUID user_ID) {
        User_ID = user_ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
