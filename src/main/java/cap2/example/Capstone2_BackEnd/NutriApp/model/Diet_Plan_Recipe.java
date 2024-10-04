package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Diet_Plan_Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Diet_Plan_Recipe_ID;


    @ManyToOne
    @JoinColumn(name = "Diet_Plan_ID")
    private Diet_Plan Diet_Plan_ID;

    @ManyToOne
    @JoinColumn(name = "Recipe_ID")
    private Recipe Recipe_ID;

    public Diet_Plan_Recipe() {
    }

    public Diet_Plan_Recipe(UUID diet_Plan_Recipe_ID, Diet_Plan diet_Plan_ID, Recipe recipe_ID) {
        Diet_Plan_Recipe_ID = diet_Plan_Recipe_ID;
        Diet_Plan_ID = diet_Plan_ID;
        Recipe_ID = recipe_ID;
    }

    public UUID getDiet_Plan_Recipe_ID() {
        return Diet_Plan_Recipe_ID;
    }

    public void setDiet_Plan_Recipe_ID(UUID diet_Plan_Recipe_ID) {
        Diet_Plan_Recipe_ID = diet_Plan_Recipe_ID;
    }

    public Diet_Plan getDiet_Plan_ID() {
        return Diet_Plan_ID;
    }

    public void setDiet_Plan_ID(Diet_Plan diet_Plan_ID) {
        Diet_Plan_ID = diet_Plan_ID;
    }

    public Recipe getRecipe_ID() {
        return Recipe_ID;
    }

    public void setRecipe_ID(Recipe recipe_ID) {
        Recipe_ID = recipe_ID;
    }
}
