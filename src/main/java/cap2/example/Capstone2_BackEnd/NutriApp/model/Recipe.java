package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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
    Double totalCalories;
    Double totalProtein;
    Double totalCarbs;
    Double totalFat;

}
