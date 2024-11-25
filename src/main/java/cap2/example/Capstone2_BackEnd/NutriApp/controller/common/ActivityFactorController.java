package cap2.example.Capstone2_BackEnd.NutriApp.controller.common;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.ActivityFactor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/activity-factor")
public class ActivityFactorController {

    @GetMapping("/all")
    public ApiResponse<List<ActivityFactor>> getAllActivityFactor() {
        ApiResponse<List<ActivityFactor>> response = new ApiResponse<>();
        List<ActivityFactor> activityFactors = Arrays.asList(ActivityFactor.values());
        response.setData(activityFactors);
        response.setMessage("All Activity Factor retrieved successfully");
        return response;
    }
}
