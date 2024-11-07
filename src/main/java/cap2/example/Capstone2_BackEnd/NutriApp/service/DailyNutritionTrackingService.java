package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.Daily_Nutrition_Tracking.DailyNutritionRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.Daily_Nutrition_Tracking.MealRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.Daily_Nutrition_Tracking.NutritionUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.Daily_Nutrition_Tracking.NutritionResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.Daily_Nutrition_TrackingMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Daily_Nutrition_Tracking;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.NutritionTrackingRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyNutritionTrackingService {
    NutritionTrackingRepository nutritionTrackingRepository;
    Daily_Nutrition_TrackingMapper dailyNutritionTrackingMapper;
    RecipeRepository recipeRepository;
    UserRepository userRepository;


    public List<NutritionResponse> getAllNutritionTracking() {
        List<Daily_Nutrition_Tracking> nutritions = nutritionTrackingRepository.findAll();

        return nutritions.stream().map(nutritionTracking -> {
            NutritionResponse nutritionResponse = dailyNutritionTrackingMapper.toNutritionResponse(nutritionTracking);
            nutritionResponse.setUser_ID(nutritionTracking.getUser().getUser_ID());
            return nutritionResponse;
        }).toList();
    }

    public NutritionResponse getNutritionTrackingById(String id) {
        Daily_Nutrition_Tracking nutrition = nutritionTrackingRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NUTRITION_NOT_FOUND)
        );
        NutritionResponse nutritionResponse = dailyNutritionTrackingMapper.toNutritionResponse(nutrition);
        nutritionResponse.setUser_ID(nutrition.getUser().getUser_ID());
        return nutritionResponse;
    }

    public NutritionResponse updateNutrition(String nutritionTrackingId, NutritionUpdateRequest request) {
        // Tìm đối tượng Daily_Nutrition_Tracking cần cập nhật
        Daily_Nutrition_Tracking nutritionTracking = nutritionTrackingRepository.findById(nutritionTrackingId)
                .orElseThrow(() -> new AppException(ErrorCode.NUTRITION_NOT_FOUND));

        // Đặt lại giá trị dinh dưỡng tổng
        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;

        // Cập nhật thông tin từ request vào entity (user, date, etc.)
//        dailyNutritionTrackingMapper.updateDaily_Nutrition_Tracking(nutritionTracking, request);

        // Lặp qua danh sách meals từ request và tính toán lại giá trị dinh dưỡng tổng
        for (NutritionUpdateRequest.MealForUpdate meal : request.getMeals()) {
            // Duyệt qua từng món ăn trong danh sách dishNames
            for (String dishName : meal.getDishNames()) {
                Recipe recipe = recipeRepository.findByRecipeName(dishName);

                // Cộng dồn các giá trị dinh dưỡng từ mỗi món ăn
                totalCalories += recipe.getTotalCalories();
                totalProtein += recipe.getTotalProtein();
                totalCarbs += recipe.getTotalCarbs();
                totalFat += recipe.getTotalFat();
            }
        }

        // Cập nhật lại giá trị dinh dưỡng tổng vào entity
        nutritionTracking.setTotalCalories(totalCalories);
        nutritionTracking.setTotalProtein(totalProtein);
        nutritionTracking.setTotalCarbs(totalCarbs);
        nutritionTracking.setTotalFat(totalFat);

        // Lưu thay đổi và trả về response
        Daily_Nutrition_Tracking updatedTracking = nutritionTrackingRepository.save(nutritionTracking);
        NutritionResponse nutritionResponse = dailyNutritionTrackingMapper.toNutritionResponse(updatedTracking);
        nutritionResponse.setUser_ID(updatedTracking.getUser().getUser_ID());
        return nutritionResponse;
    }


    public String deleteNutrition(String nutritionTrackingId) {
        Daily_Nutrition_Tracking nutritionTracking = nutritionTrackingRepository.findById(nutritionTrackingId)
                .orElseThrow(() -> new AppException(ErrorCode.NUTRITION_NOT_FOUND));
        nutritionTrackingRepository.deleteById(nutritionTrackingId);
        return "Nutrition Tracking deleted";
    }


    //create daily
    public Daily_Nutrition_Tracking createDailyNutrition(DailyNutritionRequest request) {
        // Khởi tạo tổng giá trị dinh dưỡng
        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;

        // Lấy thông tin người dùng từ database
        User user = userRepository.findById(request.getUser_ID())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (nutritionTrackingRepository.existsByUserAndDate(user.getUser_ID(), request.getDate())) {
            throw new AppException(ErrorCode.DATE_DUPLICATE);
        }

        // Duyệt qua từng bữa ăn trong danh sách meals
        for (MealRequest meal : request.getMeals()) {
            // Duyệt qua từng món ăn trong danh sách dishNames
            for (String dishName : meal.getDishNames()) {
                Recipe recipe = recipeRepository.findByRecipeName(dishName);
                // Cộng dồn các giá trị dinh dưỡng từ mỗi món ăn
                totalCalories += recipe.getTotalCalories();
                totalProtein += recipe.getTotalProtein();
                totalCarbs += recipe.getTotalCarbs();
                totalFat += recipe.getTotalFat();
            }
        }

        // Tạo một đối tượng Daily_Nutrition_Tracking mới để lưu trữ thông tin
        Daily_Nutrition_Tracking dailyNutritionTracking = new Daily_Nutrition_Tracking();
        dailyNutritionTracking.setUser(user);
        dailyNutritionTracking.setDate(request.getDate());
        dailyNutritionTracking.setTotalCalories(totalCalories);
        dailyNutritionTracking.setTotalProtein(totalProtein);
        dailyNutritionTracking.setTotalCarbs(totalCarbs);
        dailyNutritionTracking.setTotalFat(totalFat);

        // Lưu đối tượng Daily_Nutrition_Tracking vào database
        return nutritionTrackingRepository.save(dailyNutritionTracking);
    }


}
