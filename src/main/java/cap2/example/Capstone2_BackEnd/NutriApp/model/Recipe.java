package cap2.example.Capstone2_BackEnd.NutriApp.model;


import cap2.example.Capstone2_BackEnd.NutriApp.enums.DifficultyLevel;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.MealType;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.NutritionalQuality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String Recipe_ID;
    String recipeName;
    String description;
    String cookingInstructions;
    String imageURL;
    Double totalCalories;
    Double totalProtein;
    Double totalCarbs;
    Double totalFat;
    Double prepTime;
    Double cookTime;
    DifficultyLevel difficultyLevel;

    NutritionalQuality nutritionalQuality;

    @ElementCollection(targetClass = MealType.class)
    @Enumerated(EnumType.STRING)
    Set<MealType> mealType;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Recipe_Ingredient> recipeIngredients;
}
