package cap2.example.Capstone2_BackEnd.NutriApp.controller;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.RecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.SearchRecipeByIngredientsRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.RecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.service.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


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
        if ((pageNo == null && pageSize == null && sort == null) || pageNo == -1) {
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
    ApiResponse<Set<RecipeResponse>> searchByIngredient(@RequestBody SearchRecipeByIngredientsRequest request) {
        ApiResponse<Set<RecipeResponse>> apiResponse = new ApiResponse<>();
        Set<RecipeResponse> recipeResponses = recipeService.searchByIngredient(request);
        if (recipeResponses.isEmpty()) {
            apiResponse.setMessage("No recipes found with the given ingredients");
        } else {
            apiResponse.setMessage("Success");
        }
        apiResponse.setData(recipeService.searchByIngredient(request));
        return apiResponse;
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

    @GetMapping("/filterByCalories")
    public ApiResponse<List<RecipeResponse>> filterByCalories(
            @RequestParam(required = false) Double minCalories,
            @RequestParam(required = false) Double maxCalories) {
        ApiResponse<List<RecipeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(recipeService.getRecipesByCaloriesRange(minCalories, maxCalories));
        apiResponse.setMessage("Success");
        return apiResponse;
    }

    @GetMapping("/filterByProtein")
    public ApiResponse<List<RecipeResponse>> filterByProtein(
            @RequestParam(required = false) Double minProtein,
            @RequestParam(required = false) Double maxProtein) {
        ApiResponse<List<RecipeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(recipeService.getRecipesByProteinRange(minProtein, maxProtein));
        apiResponse.setMessage("Success");
        return apiResponse;
    }

    @GetMapping("/filterByCarbs")
    public ApiResponse<List<RecipeResponse>> filterByCarbs(
            @RequestParam(required = false) Double minCarbs,
            @RequestParam(required = false) Double maxCarbs) {
        ApiResponse<List<RecipeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(recipeService.getRecipesByCarbsRange(minCarbs, maxCarbs));
        apiResponse.setMessage("Success");
        return apiResponse;
    }

    @GetMapping("/filterByFat")
    public ApiResponse<List<RecipeResponse>> filterByFat(
            @RequestParam(required = false) Double minFat,
            @RequestParam(required = false) Double maxFat) {
        ApiResponse<List<RecipeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(recipeService.getRecipesByFatRange(minFat, maxFat));
        apiResponse.setMessage("Success");
        return apiResponse;
    }
}
