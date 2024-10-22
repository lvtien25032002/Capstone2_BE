package cap2.example.Capstone2_BackEnd.NutriApp.mapper;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.ingredient.IngredientCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.ingredient.IngredientUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe_ingredient.IngredientForRecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.ingredient.IngredientResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    @Mapping(target = "imageURL", ignore = true)
    Ingredient toIngredient(IngredientCreateRequest request);

    @Mapping(source = "ingredient_ID", target = "Ingredient_ID")
    IngredientResponse toIngredientResponse(Ingredient ingredient);

    @Mapping(target = "imageURL", ignore = true)
    void updateIngredient(@MappingTarget Ingredient ingredient, IngredientUpdateRequest request);

    Set<IngredientForRecipeRequest> toSetIngredientForRecipeRequest(Set<IngredientForRecipeRequest> request);
}
