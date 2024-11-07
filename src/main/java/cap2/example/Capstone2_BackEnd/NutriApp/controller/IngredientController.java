package cap2.example.Capstone2_BackEnd.NutriApp.controller;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.ingredient.IngredientCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.ingredient.IngredientUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.ingredient.IngredientResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.service.IngredientService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IngredientController {

    IngredientService ingredientService;


    @GetMapping
    Object getAllIngredients(
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String[] sort) {
        if (pageNo == null) {
            throw new IllegalArgumentException("Page number must be not null");
        }
        if ((pageNo == null && pageSize == null && sort == null) || pageNo == -1) {
            ApiResponse<List<IngredientResponse>> response = new ApiResponse<>();
            response.setData(ingredientService.getAllIngredients());
            response.setMessage("Get all Ingredients successfully");
            return response;
        } else {
            if (pageSize < 1 && pageSize != -1) {
                throw new IllegalArgumentException("Page size must be greater than 0");
            }
            pageNo = pageNo - 1;
            if (pageSize == null) {
                pageSize = 10;
            }

            return ingredientService.getPagingAllIngredients(pageNo, pageSize, sort);
        }

    }
    @GetMapping("/all")
    ApiResponse<List<IngredientResponse>> getAllRecipes() {
        ApiResponse<List<IngredientResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(ingredientService.getAllIngredients());
        apiResponse.setMessage("Success");
        return apiResponse;
    }

    @PostMapping()
    ApiResponse<IngredientResponse> createIngredient(@RequestBody @Valid IngredientCreateRequest request) {
        ApiResponse<IngredientResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(ingredientService.createIngredient(request));
        apiResponse.setMessage("Ingredient created");
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
