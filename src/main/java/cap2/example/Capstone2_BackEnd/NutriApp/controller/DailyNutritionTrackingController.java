package cap2.example.Capstone2_BackEnd.NutriApp.controller;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.DailyNutritionRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.NutritionResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.NutritionUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Daily_Nutrition_Tracking;
import cap2.example.Capstone2_BackEnd.NutriApp.service.DailyNutritionTrackingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/nutrition")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyNutritionTrackingController {
    DailyNutritionTrackingService dailyNutritionTrackingService;

    @PostMapping("/daily-tracking")
    public ResponseEntity<?> TrackingDailyNutrition(@RequestBody DailyNutritionRequest request) {
        Daily_Nutrition_Tracking dailyNutritionTracking = dailyNutritionTrackingService.createDailyNutrition(request);
        return ResponseEntity.ok(dailyNutritionTracking);
    }

    @GetMapping()
    ApiResponse<List<NutritionResponse>> getAllNutritionTracking() {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setData(dailyNutritionTrackingService.getAllNutritionTracking());
        apiResponse.setMessage("Get all nutrition tracking successfully");
        return apiResponse;
    }


    @GetMapping("/{nutritionId}")
    ApiResponse<NutritionResponse> getNutritionTracking(@PathVariable String nutritionId) {
        ApiResponse<NutritionResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(dailyNutritionTrackingService.getNutritionTrackingById(nutritionId));
        apiResponse.setMessage("Get nutrition tracking successfully");
        return apiResponse;
    }

    @PutMapping("/{nutritionId}")
    public ApiResponse<NutritionResponse> updateNutritionTracking(
            @PathVariable String nutritionId,
            @RequestBody NutritionUpdateRequest request) {

        ApiResponse<NutritionResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(dailyNutritionTrackingService.updateNutrition(nutritionId, request));
        apiResponse.setMessage("Nutrition updated successfully");

        return apiResponse;
    }

    @DeleteMapping("/{nutritionId}")
    ApiResponse<String> deleteNutritionTracking(@PathVariable String nutritionId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData(dailyNutritionTrackingService.deleteNutrition(nutritionId));
        return apiResponse;
    }
}
