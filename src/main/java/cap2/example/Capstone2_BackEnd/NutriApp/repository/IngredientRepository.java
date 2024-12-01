package cap2.example.Capstone2_BackEnd.NutriApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cap2.example.Capstone2_BackEnd.NutriApp.model.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    boolean existsByIngredientName(String ingredientName);


    Ingredient findByIngredientName(String ingredientName);


    List<Ingredient> findByIngredientNameContaining(String ingredientName);
}
