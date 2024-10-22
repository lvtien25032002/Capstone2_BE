package cap2.example.Capstone2_BackEnd.NutriApp.controller;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe.RecipeCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe.RecipeUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe.RecipeResponse;
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

    @PostMapping
    ApiResponse<RecipeResponse> createRecipe(@RequestBody RecipeCreateRequest request) {
        ApiResponse<RecipeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(recipeService.createRecipe(request));
        apiResponse.setMessage("Successfully created recipe");
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<RecipeResponse>> getAllRecipes() {
        ApiResponse<List<RecipeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(recipeService.getAllRecipes());
        apiResponse.setMessage("Success");
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
    ApiResponse<RecipeResponse> updateRecipe(@PathVariable String recipeId, @RequestBody RecipeUpdateRequest request) {
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
}
