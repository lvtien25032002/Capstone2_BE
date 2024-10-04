package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
public class Recipe_Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Recipe_Ingredient_ID;

    @OneToMany(mappedBy = "Recipe_ID")
    private Set<Recipe> Recipe_ID;

    @OneToMany(mappedBy = "Ingredient_ID")
    private Set<Ingredient> Ingredient_ID;

    public Recipe_Ingredient() {
    }

    public Recipe_Ingredient(UUID recipe_Ingredient_ID, Set<Recipe> recipe_ID, Set<Ingredient> ingredient_ID) {
        Recipe_Ingredient_ID = recipe_Ingredient_ID;
        Recipe_ID = recipe_ID;
        Ingredient_ID = ingredient_ID;
    }

    public UUID getRecipe_Ingredient_ID() {
        return Recipe_Ingredient_ID;
    }

    public void setRecipe_Ingredient_ID(UUID recipe_Ingredient_ID) {
        Recipe_Ingredient_ID = recipe_Ingredient_ID;
    }

    public Set<Recipe> getRecipe_ID() {
        return Recipe_ID;
    }

    public void setRecipe_ID(Set<Recipe> recipe_ID) {
        Recipe_ID = recipe_ID;
    }

    public Set<Ingredient> getIngredient_ID() {
        return Ingredient_ID;
    }

    public void setIngredient_ID(Set<Ingredient> ingredient_ID) {
        Ingredient_ID = ingredient_ID;
    }
}
