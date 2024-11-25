package cap2.example.Capstone2_BackEnd.NutriApp.controller.common;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.DietType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/diet-type")
public class DietTypeController {

    @GetMapping("/all")
    public ApiResponse<List<DietType>> getAllActivityFactor() {
        ApiResponse<List<DietType>> response = new ApiResponse<>();
        List<DietType> dietTypes = Arrays.asList(DietType.values());
        response.setData(dietTypes);
        response.setMessage("All diet type retrieved successfully");
        return response;
    }
}
