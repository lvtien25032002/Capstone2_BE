package cap2.example.Capstone2_BackEnd.NutriApp.repository;

import cap2.example.Capstone2_BackEnd.NutriApp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    boolean existsByIngredientName(String ingredientName);


    Ingredient findByIngredientName(String ingredientName);

    @Query("SELECT i FROM Ingredient i WHERE i.ingredientName LIKE %:ingredientName%")
    List<Ingredient> findByIngredientNameContaining(@Param("ingredientName") String ingredientName);
}
