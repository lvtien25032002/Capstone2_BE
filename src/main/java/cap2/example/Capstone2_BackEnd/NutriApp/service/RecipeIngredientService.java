package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe_ingredient.IngredientForRecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe_ingredient.RecipeIngredientUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe_ingredient.IngredientForRecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe_ingredient.RecipeIngredientResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.RecipeIngredientMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Ingredient;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe_Ingredient;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.IngredientRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeIngredientRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class RecipeIngredientService {
    RecipeIngredientRepository recipeIngredientRepository;
    RecipeIngredientMapper recipeIngredientMapper;
    RecipeRepository recipeRepository;
    IngredientRepository ingredientRepository;

    public RecipeIngredientResponse createRecipeIngredient(Recipe recipe, IngredientForRecipeRequest request) {
        Recipe_Ingredient recipeIngredient = new Recipe_Ingredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredientRepository.findByIngredientName(request.getIngredientName()));
        recipeIngredient.setQuantity(request.getQuantity());
        recipeIngredient.setUnit(request.getUnit());
        return recipeIngredientMapper.toRecipeIngredientResponse(recipeIngredientRepository.save(recipeIngredient));
    }

    public RecipeIngredientResponse getRecipeIngredient(String id) {
        return recipeIngredientMapper.toRecipeIngredientResponse(recipeIngredientRepository.findById(id).orElse(null));
    }

    public List<IngredientForRecipeResponse> getIngredientsForRecipeResponse(Recipe recipe) {
        List<Recipe_Ingredient> recipeIngredient_List = recipeIngredientRepository.findRecipe_IngredientByRecipe(recipe);
        List<IngredientForRecipeResponse> ingredientsForRecipeResponses = recipeIngredient_List.stream().map(recipe_ingredient -> {
            IngredientForRecipeResponse ingredientForRecipeResponse = new IngredientForRecipeResponse();
            Ingredient ingredient = recipe_ingredient.getIngredient();
            ingredientForRecipeResponse.setIngredientName(ingredient.getIngredientName());
            ingredientForRecipeResponse.setQuantity(recipe_ingredient.getQuantity());
            ingredientForRecipeResponse.setUnit(recipe_ingredient.getUnit());
            return ingredientForRecipeResponse;
        }).toList();
        return ingredientsForRecipeResponses;
    }

    public RecipeIngredientResponse updateRecipeIngredient(String id, RecipeIngredientUpdateRequest request) {
        Optional<Recipe_Ingredient> recipeIngredient = recipeIngredientRepository.findById(id);
        Optional<Recipe> recipe = recipeRepository.findById(request.getRecipe_ID());
        Optional<Ingredient> ingredient = ingredientRepository.findById(request.getIngredient_ID());

        if (recipeIngredient.isPresent()) {
            Recipe_Ingredient recipeIngredientEntity = recipeIngredient.get();
            if (recipe.isPresent()) {
                Recipe recipeEntity = recipe.get();
//                recipeIngredientEntity.setRecipe_ID(recipeEntity);
            } else {
                throw new AppException(ErrorCode.RECIPE_NOT_FOUND);
            }

            if (ingredient.isPresent()) {
                Ingredient ingredientEntity = ingredient.get();
//                recipeIngredientEntity.setIngredient_ID(ingredientEntity);
            } else {
                throw new AppException(ErrorCode.INGREDIENT_NOT_FOUND);
            }
            return recipeIngredientMapper.toRecipeIngredientResponse(recipeIngredientRepository.save(recipeIngredientEntity));
        } else {
            throw new AppException(ErrorCode.RECIPE_INGREDIENT_NOT_FOUND);
        }
    }

}
