package cap2.example.Capstone2_BackEnd.NutriApp.controller;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.ingredient.IngredientCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.ingredient.IngredientUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.ingredient.IngredientResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Ingredient;
import cap2.example.Capstone2_BackEnd.NutriApp.service.IngredientService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IngredientController {

    IngredientService ingredientService;


    @PostMapping()
    ApiResponse<IngredientResponse> createIngredient(@RequestBody @Valid IngredientCreateRequest request) {
        ApiResponse<IngredientResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(ingredientService.createIngredient(request));
        apiResponse.setMessage("Ingredient created");
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<Ingredient>> getAllIngredients() {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setData(ingredientService.getAllIngredients());
        apiResponse.setMessage("Get all Ingredients successfully");
        return apiResponse;
    }

    @GetMapping("/{ingredientId}")
    ApiResponse<IngredientResponse> getIngredient(@PathVariable("ingredientId") String ingredientId) {
        ApiResponse<IngredientResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(ingredientService.getIngredient(ingredientId));
        apiResponse.setMessage("Get Ingredient successfully");
        return apiResponse;
    }

    @PutMapping("/{ingredientId}")
    ApiResponse<IngredientResponse> updateIngredient(@PathVariable String ingredientId, @RequestBody IngredientUpdateRequest request) {
        ApiResponse<IngredientResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(ingredientService.updateIngredient(ingredientId, request));
        apiResponse.setMessage("Update Ingredient successfully");
        return apiResponse;
    }

    @DeleteMapping("/{ingredientId}")
    ApiResponse<String> deleteIngredient(@PathVariable String ingredientId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData(ingredientService.deleteIngredient(ingredientId));
        return apiResponse;
    }

}
