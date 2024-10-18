package cap2.example.Capstone2_BackEnd.NutriApp.model;


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
public class Recipe_Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String Recipe_Ingredient_ID;

    @OneToMany(mappedBy = "Recipe_ID")
    Set<Recipe> Recipe_ID;

    @OneToMany(mappedBy = "Ingredient_ID")
    Set<Ingredient> Ingredient_ID;

}
