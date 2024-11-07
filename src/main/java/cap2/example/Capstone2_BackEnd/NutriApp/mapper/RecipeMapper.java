package cap2.example.Capstone2_BackEnd.NutriApp.mapper;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe.RecipeCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe.RecipeUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe.RecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe toRecipe(RecipeCreateRequest request);

    @Mapping(source = "recipe_ID", target = "recipe_ID")
    RecipeResponse toRecipeResponse(Recipe recipe);

    void updateRecipe(@MappingTarget Recipe recipe, RecipeUpdateRequest request);

}
