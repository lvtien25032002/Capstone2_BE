package cap2.example.Capstone2_BackEnd.NutriApp.controller;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.MealType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/meal-types")
public class MealTypeController {
    @GetMapping("/all")
    public ApiResponse<List<MealType>> getAllMealTypes() {
        ApiResponse<List<MealType>> response = new ApiResponse<>();
        List<MealType> mealTypes = Arrays.asList(MealType.values());
        response.setData(mealTypes);
        response.setMessage("All meal types retrieved successfully");
        return response;
    }

}
