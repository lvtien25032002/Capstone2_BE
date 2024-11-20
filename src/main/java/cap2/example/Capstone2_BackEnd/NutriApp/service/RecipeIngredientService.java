package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.IngredientForRecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.IngredientForRecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.error.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.RecipeIngredientMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Ingredient;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe_Ingredient;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.IngredientRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeIngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class RecipeIngredientService {
    RecipeIngredientRepository recipeIngredientRepository;
    RecipeIngredientMapper recipeIngredientMapper;
    IngredientRepository ingredientRepository;

    public void createRecipeIngredient(Recipe recipe, IngredientForRecipeRequest request) {
        Recipe_Ingredient recipeIngredient = new Recipe_Ingredient();
        recipeIngredient.setRecipe(recipe);
        Ingredient ingredient = ingredientRepository.findById(request.getIngredientId()).orElseThrow(() -> new AppException(ErrorCode.INGREDIENT_IN_LIST_NOT_FOUND));
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setQuantity(request.getQuantity());
        recipeIngredientMapper.toRecipeIngredientResponse(recipeIngredientRepository.save(recipeIngredient));
    }

    public List<IngredientForRecipeResponse> getIngredientsForRecipeResponse(Recipe recipe) {
        List<Recipe_Ingredient> recipeIngredient_List = recipeIngredientRepository.findRecipe_IngredientByRecipe(recipe);
        return recipeIngredient_List.stream().map(recipe_ingredient -> {
            IngredientForRecipeResponse ingredientForRecipeResponse = new IngredientForRecipeResponse();
            Ingredient ingredient = recipe_ingredient.getIngredient();
            ingredientForRecipeResponse.setIngredientId(ingredient.getIngredient_ID());
            ingredientForRecipeResponse.setIngredientName(ingredient.getIngredientName());
            ingredientForRecipeResponse.setQuantity(recipe_ingredient.getQuantity());
            ingredientForRecipeResponse.setUnit(ingredient.getUnit());
            return ingredientForRecipeResponse;
        }).toList();
    }

}
