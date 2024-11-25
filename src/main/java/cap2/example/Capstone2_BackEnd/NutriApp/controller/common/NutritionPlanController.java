package cap2.example.Capstone2_BackEnd.NutriApp.controller.common;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.NutritionPlan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/nutrition-plan")
public class NutritionPlanController {

    @GetMapping("/all")
    public ApiResponse<List<NutritionPlan>> getAllActivityFactor() {
        ApiResponse<List<NutritionPlan>> response = new ApiResponse<>();
        List<NutritionPlan> nutritionPlans = Arrays.asList(NutritionPlan.values());
        response.setData(nutritionPlans);
        response.setMessage("All nutrition plan retrieved successfully");
        return response;
    }
}
