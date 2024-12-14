package cap2.example.Capstone2_BackEnd.NutriApp.controller.common;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.user.NutritionPlanResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.NutritionPlan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/nutrition-plan")
public class NutritionPlanController {

    @GetMapping("/all")
    public ApiResponse<List<NutritionPlanResponse>> getAllNutritionPlan() {
        ApiResponse<List<NutritionPlanResponse>> response = new ApiResponse<>();
        List<NutritionPlanResponse> nutritionPlanResponses = new ArrayList<>();
        Arrays.asList(NutritionPlan.values()).stream().map(NutritionPlan -> {
            NutritionPlanResponse nutritionPlanResponse = new NutritionPlanResponse();
            nutritionPlanResponse.setNutritionPlanName(NutritionPlan.name());
            nutritionPlanResponse.setDescription(NutritionPlan.getDescription());
            nutritionPlanResponse.setProteinPercentage(NutritionPlan.getProtein());
            nutritionPlanResponse.setCarbsPercentage(NutritionPlan.getCarbs());
            nutritionPlanResponse.setFatPercentage(NutritionPlan.getFat());
            return nutritionPlanResponse;
        }).forEach(nutritionPlanResponses::add);
        response.setData(nutritionPlanResponses);
        response.setMessage("All nutrition plan retrieved successfully");
        return response;
    }

    @GetMapping("/{NutritionPlanName}")
    public ApiResponse<NutritionPlanResponse> getNutritionPlan(@PathVariable String NutritionPlanName) {
        ApiResponse<NutritionPlanResponse> response = new ApiResponse<>();

        NutritionPlan nutritionPlan;
        try {
            nutritionPlan = NutritionPlan.valueOf(NutritionPlanName);
        } catch (IllegalArgumentException e) {
            response.setMessage("Invalid nutrition plan name");
            return response;
        }
        NutritionPlanResponse nutritionPlanResponse = new NutritionPlanResponse();
        nutritionPlanResponse.setNutritionPlanName(nutritionPlan.name());
        nutritionPlanResponse.setDescription(nutritionPlan.getDescription());
        nutritionPlanResponse.setProteinPercentage(nutritionPlan.getProtein());
        nutritionPlanResponse.setCarbsPercentage(nutritionPlan.getCarbs());
        nutritionPlanResponse.setFatPercentage(nutritionPlan.getFat());

        response.setData(nutritionPlanResponse);
        response.setMessage("All nutrition plan retrieved successfully");
        return response;
    }

    @GetMapping("/filterByDietType/{dietType}")
    public ApiResponse<List<NutritionPlanResponse>> getAllNutritionPlanBasedOnDietType(@PathVariable String dietType) {
        ApiResponse<List<NutritionPlanResponse>> response = new ApiResponse<>();
        List<NutritionPlanResponse> nutritionPlanResponses = new ArrayList<>();
        log.info("Diet Type: {}", dietType);
        ArrayList<String> dietTypes = new ArrayList<>();
        if (dietType.equals("WEIGHT_LOSS")) {
            dietTypes.add("DURABLE");
            dietTypes.add("HIGH_PROTEIN");
            dietTypes.add("LOW_CARB");
            dietTypes.add("ATHLETE");
            dietTypes.add("CARDIO_TRAINING");
        } else if (dietType.equals("WEIGHT_MAINTAIN")) {
            dietTypes.add("BALANCED");
            dietTypes.add("AFTER_WEIGHT_LOSS");
            dietTypes.add("MAINTAIN_MUSCLES");
            dietTypes.add("ATHLETE");
            dietTypes.add("CARDIO_TRAINING");
        } else if (dietType.equals("WEIGHT_GAIN")) {
            dietTypes.add("HIGH_ENERGY");
            dietTypes.add("BUILD_MUSCLE");
            dietTypes.add("ATHLETE");
            dietTypes.add("CARDIO_TRAINING");
        } else {
            response.setMessage("Invalid diet type");
            return response;
        }
        Arrays.asList(NutritionPlan.values()).stream()
                .filter(NutritionPlan -> dietTypes.contains(NutritionPlan.name()))
                .map(NutritionPlan -> {
                    NutritionPlanResponse nutritionPlanResponse = new NutritionPlanResponse();
                    nutritionPlanResponse.setNutritionPlanName(NutritionPlan.name());
                    nutritionPlanResponse.setDescription(NutritionPlan.getDescription());
                    nutritionPlanResponse.setProteinPercentage(NutritionPlan.getProtein());
                    nutritionPlanResponse.setCarbsPercentage(NutritionPlan.getCarbs());
                    nutritionPlanResponse.setFatPercentage(NutritionPlan.getFat());
                    return nutritionPlanResponse;
                }).forEach(nutritionPlanResponses::add);
        response.setData(nutritionPlanResponses);
        response.setMessage("All nutrition plan retrieved successfully");
        return response;
    }
}
