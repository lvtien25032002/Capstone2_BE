package cap2.example.Capstone2_BackEnd.NutriApp.model;


import cap2.example.Capstone2_BackEnd.NutriApp.enums.IngredientType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String Ingredient_ID;
    String ingredientName;
    IngredientType ingredientType;
    String ingredientDescription;
    String unit;

    String imageURL;

    double calories;
    double protein;
    double fat;
    double carbs;
}
