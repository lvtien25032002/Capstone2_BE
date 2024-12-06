package cap2.example.Capstone2_BackEnd.NutriApp.controller;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.IngredientRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.IngredientResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.error.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
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
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(required = false) String[] sort,
            @RequestParam(required = false) String search
    ) {
        if (pageNo == null) {
            throw new AppException(ErrorCode.PAGE_NUMBER_IS_NOT_NULL);
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (pageNo < 1 && pageNo != -1) {
            throw new AppException(ErrorCode.PAGE_NUMBER_INVALID);
        }
        if (!(search == null)) {
            pageNo = pageNo - 1;
            return ingredientService.searchIngredientByName(pageNo, pageSize, sort, search);
        }
        if (pageNo == -1) {
            ApiResponse<List<IngredientResponse>> response = new ApiResponse<>();
            response.setData(ingredientService.getAllIngredients());
            response.setMessage("Get all Ingredients successfully");
            return response;
        } else {
            pageNo = pageNo - 1;
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
    ApiResponse<IngredientResponse> createIngredient(@RequestBody @Valid IngredientRequest request) {
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
    ApiResponse<IngredientResponse> updateIngredient(@PathVariable String ingredientId, @RequestBody IngredientRequest request) {
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
