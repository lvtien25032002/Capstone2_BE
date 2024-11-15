package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.request.DailyNutritionRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.request.MealRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.response.MealResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.response.NutritionResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.response.RecipeForDailyTrackingResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.response.TrackingResponseBasedOnDate;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking_Detail.DailyNutritionTrackingDetailRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.MealType;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.Daily_Nutrition_TrackingMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Daily_Nutrition_Tracking;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Daily_Nutrition_Tracking_Detail;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.DailyNutritionTrackingDetailRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.NutritionTrackingRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyNutritionTrackingService {
    NutritionTrackingRepository nutritionTrackingRepository;
    Daily_Nutrition_TrackingMapper dailyNutritionTrackingMapper;

    RecipeRepository recipeRepository;
    UserRepository userRepository;

    DailyNutritionTrackingDetailService dailyNutritionTrackingDetailService;
    DailyNutritionTrackingDetailRepository dailyNutritionTrackingDetailRepository;


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
                () -> new AppException(ErrorCode.NUTRITION_TRACKING_NOT_FOUND)
        );
        NutritionResponse nutritionResponse = dailyNutritionTrackingMapper.toNutritionResponse(nutrition);
        nutritionResponse.setUser_ID(nutrition.getUser().getUser_ID());
        return nutritionResponse;
    }

    public String deleteNutrition(String nutritionTrackingId) {
        Daily_Nutrition_Tracking nutritionTracking = nutritionTrackingRepository.findById(nutritionTrackingId)
                .orElseThrow(() -> new AppException(ErrorCode.NUTRITION_TRACKING_NOT_FOUND));
        nutritionTrackingRepository.deleteById(nutritionTrackingId);
        return "Nutrition Tracking deleted";
    }


    //create daily
    @Transactional
    public NutritionResponse createDailyNutrition(DailyNutritionRequest request) {
        return saveAndReturnDailyNutritionTracking(request, null);
    }

    @Transactional
    public NutritionResponse updateNutrition(String nutritionTrackingId, DailyNutritionRequest request) {
        return saveAndReturnDailyNutritionTracking(request, nutritionTrackingId);
    }

    // Get Daily Nutrition Tracking by User and Date
    public TrackingResponseBasedOnDate getNutritionTrackingUserByDate(String userId, LocalDate date) {
        if (!userRepository.existsById(userId)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        if (date == null) {
            throw new AppException(ErrorCode.DATE_IS_NEED_FOR_REQUEST);
        }
        Daily_Nutrition_Tracking nutritionTracking = nutritionTrackingRepository.findDaily_Nutrition_TrackingByUserAndDate(userId, date);
        if (nutritionTracking == null) {
            throw new AppException(ErrorCode.DAILY_NUTRITION_TRACKING_FOR_DATE_IS_EMPTY);
        }
        List<Daily_Nutrition_Tracking_Detail> dailyNutritionTrackingDetails =
                dailyNutritionTrackingDetailRepository.findDaily_Nutrition_Tracking_DetailsByDaily_Nutrition_Tracking_ID(nutritionTracking.getDaily_Nutrition_Tracking_ID());
        List<MealResponse> meals = new ArrayList<>();
        for (MealType meal : MealType.values()) {
            MealResponse mealResponse = MealResponse.builder()
                    .mealType(meal.toString())
                    .recipeList(new ArrayList<>())
                    .build();
            for (Daily_Nutrition_Tracking_Detail detail : dailyNutritionTrackingDetails) {
                if (detail.getMealType().equals(meal)) {
                    mealResponse.getRecipeList().add(
                            RecipeForDailyTrackingResponse.builder()
                                    .recipeID(detail.getRecipe_ID().getRecipe_ID())
                                    .recipeName(detail.getRecipe_ID().getRecipeName())
                                    .calories(detail.getRecipe_ID().getTotalCalories())
                                    .protein(detail.getRecipe_ID().getTotalProtein())
                                    .carbs(detail.getRecipe_ID().getTotalCarbs())
                                    .fat(detail.getRecipe_ID().getTotalFat())
                                    .build()
                    );
                }

            }
            if (!mealResponse.getRecipeList().isEmpty()) {
                meals.add(mealResponse);
            }
        }
        return TrackingResponseBasedOnDate.builder()
                .date(date)
                .calories(nutritionTracking.getCalories())
                .protein(nutritionTracking.getProtein())
                .fat(nutritionTracking.getFat())
                .carbs(nutritionTracking.getCarbs())
                .meals(meals)
                .build();
    }

    public List<TrackingResponseBasedOnDate> getNutritionTrackingByUser(String userId) {
        List<Daily_Nutrition_Tracking> nutritritionTrackingList = nutritionTrackingRepository.findDaily_Nutrition_TrackingByUser(userId);
        if (nutritritionTrackingList.isEmpty()) {
            throw new AppException(ErrorCode.NUTRITION_TRACKING_BY_USER_NOT_FOUND);
        }
        List<TrackingResponseBasedOnDate> trackingResponseBasedOnDateList = new ArrayList<>();
        for (Daily_Nutrition_Tracking nutritionTracking : nutritritionTrackingList) {
            trackingResponseBasedOnDateList.add(getNutritionTrackingUserByDate(userId, nutritionTracking.getDate()));
        }
        return trackingResponseBasedOnDateList;
    }


    // --------------------------------------------------------------------------------
    // Private Method for Business Logic for Daily Nutrition Tracking

    @Transactional
    protected NutritionResponse saveAndReturnDailyNutritionTracking(DailyNutritionRequest request, String nutritionTrackingId) {
        validCheckerRequestForNutrionTracking(request);
        if (nutritionTrackingId == null) {
            if (nutritionTrackingRepository.existsByUserAndDate(request.getUser_ID(), request.getDate())) {
                throw new AppException(ErrorCode.DATE_DUPLICATE);
            }
        }

        // Khởi tạo tổng giá trị dinh dưỡng
        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;

        // Lấy thông tin người dùng từ database
        User user = userRepository.findById(request.getUser_ID())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Khởi tạo đối tượng Daily_Nutrition_Tracking
        Daily_Nutrition_Tracking dailyNutritionTracking = new Daily_Nutrition_Tracking();
        if (nutritionTrackingId != null) {
            dailyNutritionTracking = nutritionTrackingRepository.findById(nutritionTrackingId)
                    .orElseThrow(() -> new AppException(ErrorCode.NUTRITION_TRACKING_NOT_FOUND));
            dailyNutritionTrackingDetailRepository.DeleteDaily_Nutrition_Tracking_DetailsByDaily_Nutrition_Tracking_ID(nutritionTrackingId);
            dailyNutritionTrackingMapper.updateDaily_Nutrition_Tracking(dailyNutritionTracking, request);

        } else {
            UUID uuid = UUID.randomUUID();
            dailyNutritionTracking.setDaily_Nutrition_Tracking_ID(uuid.toString());
        }


        // Duyệt qua từng bữa ăn trong danh sách meals
        for (MealRequest meal : request.getMeals()) {
            // Duyệt qua từng món ăn trong danh sách Recipe List
            for (String dishName : meal.getRecipeList()) {
                Recipe recipe = recipeRepository.findByRecipeName(dishName);
                totalCalories += recipe.getTotalCalories();
                totalProtein += recipe.getTotalProtein();
                totalCarbs += recipe.getTotalCarbs();
                totalFat += recipe.getTotalFat();
            }
        }
        // Lưu đối tượng Daily_Nutrition_Tracking
        dailyNutritionTracking.setUser(user);
        dailyNutritionTracking.setDate(request.getDate());
        dailyNutritionTracking.setCalories(totalCalories);
        dailyNutritionTracking.setProtein(totalProtein);
        dailyNutritionTracking.setCarbs(totalCarbs);
        dailyNutritionTracking.setFat(totalFat);
        dailyNutritionTracking = nutritionTrackingRepository.save(dailyNutritionTracking);

        for (MealRequest meal : request.getMeals()) {
            // Duyệt qua từng món ăn trong danh sách Recipe List
            for (String dishName : meal.getRecipeList()) {
                Recipe recipe = recipeRepository.findByRecipeName(dishName);
                dailyNutritionTrackingDetailService.createDailyNutritionTrackingDetail(
                        DailyNutritionTrackingDetailRequest.builder()
                                .daily_Nutrition_Tracking_ID(dailyNutritionTracking.getDaily_Nutrition_Tracking_ID())
                                .recipe_ID(recipe.getRecipe_ID())
                                .mealType(meal.getMealType())
                                .build()
                );
            }
        }
        // Lưu đối tượng Daily_Nutrition_Tracking vào database
        NutritionResponse nutritionResponse = dailyNutritionTrackingMapper.toNutritionResponse(nutritionTrackingRepository.save(dailyNutritionTracking));
        nutritionResponse.setUser_ID(user.getUser_ID());
        return nutritionResponse;
    }


    // Checker Method
    void validCheckerRequestForNutrionTracking(DailyNutritionRequest request) {
        for (MealRequest meal : request.getMeals()) {
            try {
                var checkMealType = MealType.valueOf(meal.getMealType());
            } catch (IllegalArgumentException e) {
                throw new AppException(ErrorCode.MEAL_TYPE_INVALID);
            }
            for (String dishName : meal.getRecipeList()) {
                Recipe recipe = recipeRepository.findByRecipeName(dishName);
                if (recipe == null) {
                    throw new AppException(ErrorCode.RECIPE_IN_DISHNAME_NOT_FOUND);
                }

            }
        }

    }

}
