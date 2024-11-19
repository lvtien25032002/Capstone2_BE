package cap2.example.Capstone2_BackEnd.NutriApp.controller;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.request.DailyMealRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.response.NutritionResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.response.TrackingResponseBasedOnDate;
import cap2.example.Capstone2_BackEnd.NutriApp.service.DailyNutritionTrackingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/daily-tracking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyNutritionTrackingController {
    DailyNutritionTrackingService dailyNutritionTrackingService;

    //    @PostMapping("")
//    public ApiResponse<NutritionResponse> createTrackingDailyNutrition(@RequestBody DailyNutritionRequest request) {
//        ApiResponse<NutritionResponse> apiResponse = new ApiResponse<>();
//        apiResponse.setData(dailyNutritionTrackingService.createDailyNutrition(request));
//        apiResponse.setMessage("Create nutrition tracking successfully");
//        return apiResponse;
//    }
    @PostMapping("")
    public ApiResponse<NutritionResponse> createTrackingDailyNutrition(@RequestBody DailyMealRequest request) {
        ApiResponse<NutritionResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(dailyNutritionTrackingService.createDailyNutrition(request));
        apiResponse.setMessage("Create nutrition tracking successfully");
        return apiResponse;
    }

    @GetMapping()
    ApiResponse<List<NutritionResponse>> getAllNutritionTracking() {
        ApiResponse<List<NutritionResponse>> apiResponse = new ApiResponse<>();
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
            @RequestBody DailyMealRequest request) {
        ApiResponse<NutritionResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(dailyNutritionTrackingService.updateNutrition(nutritionId, request));
        apiResponse.setMessage("Updated nutrition tracking successfully");
        return apiResponse;
    }

    @DeleteMapping("/{nutritionId}")
    ApiResponse<String> deleteNutritionTracking(@PathVariable String nutritionId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData(dailyNutritionTrackingService.deleteNutrition(nutritionId));
        return apiResponse;
    }


    @GetMapping("/detail/{userId}")
    ApiResponse<TrackingResponseBasedOnDate> getNutritionTrackingDetail(
            @PathVariable String userId,
            @RequestParam LocalDate date
    ) {
        ApiResponse<TrackingResponseBasedOnDate> apiResponse = new ApiResponse<>();
        apiResponse.setData(dailyNutritionTrackingService.getNutritionTrackingUserByDate(userId, date));
        apiResponse.setMessage("Get nutrition tracking detail for Entry Date of User successfully");
        return apiResponse;
    }


}
