package cap2.example.Capstone2_BackEnd.NutriApp.mapper;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.RecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.RecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    @Mapping(ignore = true, target = "totalCalories")
    @Mapping(ignore = true, target = "totalProtein")
    @Mapping(ignore = true, target = "totalFat")
    @Mapping(ignore = true, target = "totalCarbs")
    Recipe toRecipe(RecipeRequest request);

    @Mapping(source = "recipe_ID", target = "recipe_ID")
    RecipeResponse toRecipeResponse(Recipe recipe);

    void updateRecipe(@MappingTarget Recipe recipe, RecipeRequest request);

}
