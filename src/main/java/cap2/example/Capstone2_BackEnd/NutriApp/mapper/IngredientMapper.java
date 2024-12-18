package cap2.example.Capstone2_BackEnd.NutriApp.mapper;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.IngredientRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.IngredientResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.IngredientForRecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    Ingredient toIngredient(IngredientRequest request);

    @Mapping(source = "ingredient_ID", target = "Ingredient_ID")
    IngredientResponse toIngredientResponse(Ingredient ingredient);


    void updateIngredient(@MappingTarget Ingredient ingredient, IngredientRequest request);

    Set<IngredientForRecipeRequest> toSetIngredientForRecipeRequest(Set<IngredientForRecipeRequest> request);
}
