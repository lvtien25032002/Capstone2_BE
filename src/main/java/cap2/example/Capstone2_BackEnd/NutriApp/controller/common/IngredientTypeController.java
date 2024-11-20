package cap2.example.Capstone2_BackEnd.NutriApp.controller.common;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ingredient.IngredientType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ingredient-types")
public class IngredientTypeController {
    @GetMapping("/all")
    public ApiResponse<List<IngredientType>> getAllIngredientTypes() {
        ApiResponse<List<IngredientType>> response = new ApiResponse<>();
        List<IngredientType> ingredientTypes = Arrays.asList(IngredientType.values());
        response.setData(ingredientTypes);
        response.setMessage("All meal types retrieved successfully");
        return response;
    }
}
