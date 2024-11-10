package cap2.example.Capstone2_BackEnd.NutriApp.service;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking_Detail.DailyNutritionTrackingDetailRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.MealType;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Daily_Nutrition_Tracking_Detail;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.DailyNutritionTrackingDetailRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.NutritionTrackingRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyNutritionTrackingDetailService {
    DailyNutritionTrackingDetailRepository dailyNutritionTrackingDetailRepository;
    NutritionTrackingRepository nutritionTrackingRepository;
    RecipeRepository recipeRepository;

    Daily_Nutrition_Tracking_Detail createDailyNutritionTrackingDetail(DailyNutritionTrackingDetailRequest request) {
        Daily_Nutrition_Tracking_Detail dailyNutritionTrackingDetail = new Daily_Nutrition_Tracking_Detail();
        dailyNutritionTrackingDetail.setDaily_Nutrition_Tracking_ID(
                nutritionTrackingRepository.findById(request.getDaily_Nutrition_Tracking_ID())
                        .orElseThrow(() -> new AppException(ErrorCode.NUTRITION_TRACKING_NOT_FOUND))
        );
        dailyNutritionTrackingDetail.setRecipe_ID(
                recipeRepository.findById(request.getRecipe_ID())
                        .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND))
        );
        try {
            dailyNutritionTrackingDetail.setMealType(MealType.valueOf(request.getMealType()));
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.MEAL_TYPE_INVALID);
        }


        return dailyNutritionTrackingDetailRepository.save(dailyNutritionTrackingDetail);
    }

}
