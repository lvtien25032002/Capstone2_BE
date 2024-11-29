package cap2.example.Capstone2_BackEnd.NutriApp.controller;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.PagingAndSortingAPIResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.request.RecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.request.SearchRecipeByIngredientsRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.response.RecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.service.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeController {
    RecipeService recipeService;


    @GetMapping
    Object getAllRecipes(
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String[] sort) {
        if (pageNo == null) {
            throw new IllegalArgumentException("Page number must be not null");
        }
        if (pageNo == -1) {
            ApiResponse<List<RecipeResponse>> apiResponse = new ApiResponse<>();
            apiResponse.setData(recipeService.getAllRecipes());
            apiResponse.setMessage("Success");
            return apiResponse;
        } else {
            if (pageSize < 1 && pageSize != -1) {
                throw new IllegalArgumentException("Page size must be greater than 0");
            }
            pageNo = pageNo - 1;
            if (pageSize == null) {
                pageSize = 10;
            }
            return recipeService.getPagingAllRecipes(pageNo, pageSize, sort);
        }
    }

    @GetMapping("/all")
    ApiResponse<List<RecipeResponse>> getAllRecipes() {
        ApiResponse<List<RecipeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(recipeService.getAllRecipes());
        apiResponse.setMessage("Success");
        return apiResponse;
    }

    @PostMapping("/searchByIngredient")
    PagingAndSortingAPIResponse<RecipeResponse> searchByIngredient(
            @RequestBody SearchRecipeByIngredientsRequest request,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String[] sort) {
        PagingAndSortingAPIResponse<RecipeResponse> recipeResponses = recipeService.searchByIngredient(request, pageNo, pageSize, sort);
        if (recipeResponses.getData().isEmpty()) {
            recipeResponses.setMessage("No recipes found with the given ingredients");
        } else {
            recipeResponses.setMessage("Success");
        }
        return recipeResponses;
    }

    @PostMapping
    ApiResponse<RecipeResponse> createRecipe(@RequestBody RecipeRequest request) {
        ApiResponse<RecipeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(recipeService.createRecipe(request));
        apiResponse.setMessage("Successfully created recipe");
        return apiResponse;
    }

    @GetMapping("/{recipeId}")
    ApiResponse<RecipeResponse> getRecipeById(@PathVariable String recipeId) {
        ApiResponse<RecipeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(recipeService.getRecipe(recipeId));
        apiResponse.setMessage("Success");
        return apiResponse;
    }

    @PutMapping("/{recipeId}")
    ApiResponse<RecipeResponse> updateRecipe(@PathVariable String recipeId, @RequestBody RecipeRequest request) {
        ApiResponse<RecipeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(recipeService.updateRecipe(recipeId, request));
        apiResponse.setMessage("Success");
        return apiResponse;
    }

    @DeleteMapping("/{recipeId}")
    ApiResponse<String> deleteRecipe(@PathVariable String recipeId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData(recipeService.deleteRecipe(recipeId));
        return apiResponse;
    }


    @GetMapping("/filter")
    public Object filterByMealType(
            @RequestParam(required = false) String mealType,
            @RequestParam(required = false) String nutritionalQuality,
            @RequestParam(required = false) String difficultyLevel,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String[] sort
    ) {
        pageNo = pageNo - 1;
        if (mealType != null) {
            return recipeService.getRecipesByMealType(mealType, pageNo, pageSize, sort);
        } else if (nutritionalQuality != null) {
            return recipeService.getRecipesByNutritionalQuality(nutritionalQuality, pageNo, pageSize, sort);
        } else if (difficultyLevel != null) {
            return recipeService.getRecipesByDifficultyLevel(difficultyLevel, pageNo, pageSize, sort);
        } else {
            throw new IllegalArgumentException("Please provide either mealType or nutritionalQuality");
        }
    }

    @GetMapping("/filterByMacroNutrients")
    public Object filterByMacroNutrients(
            @RequestParam(required = true) String macroNutrient,
            @RequestParam(required = false) Double minMacro,
            @RequestParam(required = false) Double maxMacro,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String[] sort
    ) {
        pageNo = pageNo - 1;
        return recipeService.getRecipesByMacroNutrients(macroNutrient, minMacro, maxMacro, pageNo, pageSize, sort);
    }

    @GetMapping("/search")
    public Object searchRecipeByName(
            @RequestParam(required = true) String recipeName,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String[] sort) {
        pageNo = pageNo - 1;
        return recipeService.searchRecipeByName(recipeName, pageNo, pageSize, sort);
    }
}
