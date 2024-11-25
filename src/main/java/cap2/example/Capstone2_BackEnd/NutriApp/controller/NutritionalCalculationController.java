package cap2.example.Capstone2_BackEnd.NutriApp.controller;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.nutritionalCalculation.NutritionalCalculationResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.response.RecipeResponseBaseOnNutritionPlan;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.error.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository.UserRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.service.NutritionalCalculationService;
import cap2.example.Capstone2_BackEnd.NutriApp.service.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/nutritional-calculation")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NutritionalCalculationController {
    // Service
    NutritionalCalculationService nutritionalCalculationService;
    RecipeService recipeService;

    // Repository
    UserRepository userRepository;
    // Mapper


    @GetMapping("/getCalculation/{userId}")
    public ApiResponse<NutritionalCalculationResponse> getNutritionalCalculation(
            @PathVariable String userId
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        ApiResponse<NutritionalCalculationResponse> response = new ApiResponse<>();
        response.setMessage("Nutritional calculation For User Successfully");
        response.setData(nutritionalCalculationService.calculateNutrition(user));
        return response;
    }


    @GetMapping("/getRecipe/{userId}")
    public ApiResponse<RecipeResponseBaseOnNutritionPlan> getRecipeBasedOnNutritionalCalculation(
            @PathVariable String userId
    ) {
        ApiResponse<RecipeResponseBaseOnNutritionPlan> response = new ApiResponse<>();
        response.setMessage("Recipe Based On Nutritional Calculation For User Successfully");
        response.setData(recipeService.getRecipeBasedOnNutritionalCalculation(userId));
        return response;
    }
}
