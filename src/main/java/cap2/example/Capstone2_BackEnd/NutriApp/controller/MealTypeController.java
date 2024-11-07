package cap2.example.Capstone2_BackEnd.NutriApp.controller;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.MealTypeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.MealType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;import java.util.stream.Collectors;

@RestController
@RequestMapping("/meal-types")
public class MealTypeController {
    @GetMapping("/all")
    public ApiResponse<List<MealTypeResponse>> getAllMealTypes() {
        ApiResponse<List<MealTypeResponse>> response = new ApiResponse<>();
        List<MealTypeResponse> mealTypes = Arrays.stream(MealType.values())
                .map(mealType -> MealTypeResponse.builder()
                        .name(mealType.name())
                        .displayName(mealType.getDisplayName())
                        .build())
                .toList();
        response.setData(mealTypes);
        response.setMessage("All meal types retrieved successfully");
        return response;
    }

}
