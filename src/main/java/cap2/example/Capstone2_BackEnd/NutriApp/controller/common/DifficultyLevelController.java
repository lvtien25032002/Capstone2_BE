package cap2.example.Capstone2_BackEnd.NutriApp.controller.common;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.DifficultyLevel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/difficulty-level")
public class DifficultyLevelController {
    @GetMapping("/all")
    public ApiResponse<List<DifficultyLevel>> getAllDifficultyLevel() {
        ApiResponse<List<DifficultyLevel>> response = new ApiResponse<>();
        List<DifficultyLevel> difficultyLevels = Arrays.asList(DifficultyLevel.values());
        response.setData(difficultyLevels);
        response.setMessage("All Difficulty Level retrieved successfully");
        return response;
    }

}
