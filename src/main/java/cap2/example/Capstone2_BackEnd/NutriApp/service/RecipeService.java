package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe.RecipeCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe.RecipeUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe.RecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.RecipeMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeService {
    RecipeRepository recipeRepository;
    RecipeMapper recipeMapper;

    public RecipeResponse createRecipe(RecipeCreateRequest request) {
        if (recipeRepository.existsByRecipeName(request.getRecipeName()))
            throw new AppException(ErrorCode.RECIPE_EXIST);

        Recipe recipe = recipeMapper.toRecipe(request);
        return recipeMapper.toRecipeResponse(recipeRepository.save(recipe));
    }

    public List<RecipeResponse> getAllRecipes() {
        var recipes = recipeRepository.findAll();
        return recipes.stream().map(recipeMapper::toRecipeResponse).toList();
    }

    public RecipeResponse getRecipe(String id) {
        return recipeMapper.toRecipeResponse(recipeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND)));
    }


    public RecipeResponse updateRecipe(String id, RecipeUpdateRequest request) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND));
        recipeMapper.updateRecipe(recipe, request);

        return recipeMapper.toRecipeResponse(recipeRepository.save(recipe));
    }

    public String deleteRecipe(String id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND));
        recipeRepository.deleteById(id);
        return "Delete Recipe Successfully";
    }
}

