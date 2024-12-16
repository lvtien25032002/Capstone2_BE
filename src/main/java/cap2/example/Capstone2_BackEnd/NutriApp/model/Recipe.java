package cap2.example.Capstone2_BackEnd.NutriApp.model;


import cap2.example.Capstone2_BackEnd.NutriApp.enums.recipe.DifficultyLevel;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.recipe.MealType;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.recipe.NutritionalQuality;
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
    @Column(name = "description", length = 1000)
    String description;
    @Column(name = "cooking_instructions", length = 2000)
    String cookingInstructions;
    String imageURL;
    int totalCalories;
    int totalProtein;
    int totalCarbs;
    int totalFat;
    Double prepTime;
    Double cookTime;
    DifficultyLevel difficultyLevel;


    @ElementCollection(targetClass = NutritionalQuality.class)
    @Enumerated(EnumType.STRING)
    Set<NutritionalQuality> nutritionalQuality;

    @ElementCollection(targetClass = MealType.class)
    @Enumerated(EnumType.STRING)
    Set<MealType> mealType;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Recipe_Ingredient> recipeIngredients;
}
