package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
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
    String ingredientType;
    String ingredientDescription;

    @ManyToOne
    @JoinColumn(name = "image_id", unique = false)
    Image imageURL;

    double calories;
    double protein;
    double fat;
    double carbs;
}
