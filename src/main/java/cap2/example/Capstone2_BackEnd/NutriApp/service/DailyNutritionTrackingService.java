package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.request.DailyMealRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.response.MealResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.response.NutritionResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.response.NutritionResponseForMealType;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking.response.TrackingResponseBasedOnDate;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.daily_nutrition_tracking_detail.DailyNutritionTrackingDetailRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.response.SimpleRecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.error.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.recipe.MealType;
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
            NutritionResponse nutritionResponse = toDailyNutritionResponse(nutritionTracking);
            return nutritionResponse;
        }).toList();
    }

    public NutritionResponseForMealType getNutritionTrackingById(String id) {
        Daily_Nutrition_Tracking nutrition = nutritionTrackingRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NUTRITION_TRACKING_NOT_FOUND)
        );
        // Response for Nutrition Tracking
        List<String> recipeList = new ArrayList<>();
        List<Daily_Nutrition_Tracking_Detail> dailyNutritionTrackingDetailList =
                dailyNutritionTrackingDetailRepository.findDaily_Nutrition_Tracking_DetailsByDaily_Nutrition_Tracking_ID(id);
        for (Daily_Nutrition_Tracking_Detail detail : dailyNutritionTrackingDetailList) {
            recipeList.add(detail.getRecipe_ID().getRecipe_ID().toString());
        }
        //
        NutritionResponseForMealType nutritionResponse = NutritionResponseForMealType.builder()
                .Daily_Nutrition_Tracking_ID(nutrition.getDaily_Nutrition_Tracking_ID())
                .user_ID(nutrition.getUser().getUser_ID())
                .date(nutrition.getDate())
                .MealType(nutrition.getMealType().toString())
                .recipeList(recipeList)
                .build();
        return nutritionResponse;
    }

    public String deleteNutrition(String nutritionTrackingId) {
        Daily_Nutrition_Tracking nutritionTracking = nutritionTrackingRepository.findById(nutritionTrackingId)
                .orElseThrow(() -> new AppException(ErrorCode.NUTRITION_TRACKING_NOT_FOUND));
        nutritionTrackingRepository.deleteById(nutritionTrackingId);
        return "Nutrition Tracking deleted";
    }

    @Transactional
    public NutritionResponse createDailyNutrition(DailyMealRequest request) {
        return createAndUpdateDailyMeal(request, null);
    }

    @Transactional
    public NutritionResponse updateNutrition(String nutritionTrackingId, DailyMealRequest request) {
        return createAndUpdateDailyMeal(request, nutritionTrackingId);
    }

    // Get Daily Nutrition Tracking by User and Date
    public TrackingResponseBasedOnDate getNutritionTrackingUserByDate(String userId, LocalDate date) {
        if (!userRepository.existsById(userId)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        if (date == null) {
            throw new AppException(ErrorCode.DATE_IS_NEED_FOR_REQUEST);
        }
        List<Daily_Nutrition_Tracking> nutritionTrackingList = nutritionTrackingRepository.findDaily_Nutrition_TrackingByUserAndDate(userId, date);
        if (nutritionTrackingList == null || nutritionTrackingList.isEmpty()) {
            throw new AppException(ErrorCode.DAILY_NUTRITION_TRACKING_FOR_DATE_IS_EMPTY);
        }

        // Initial Default Value for Tracking Response Based on Date
        long totalCalories = 0;
        long totalProtein = 0;
        long totalFat = 0;
        long totalCarbs = 0;
        TrackingResponseBasedOnDate trackingResponseBasedOnDate = new TrackingResponseBasedOnDate();
        trackingResponseBasedOnDate.setDate(date);
        List<MealResponse> mealResponseList = new ArrayList<>();

        for (Daily_Nutrition_Tracking tracking : nutritionTrackingList) {
            totalCalories += tracking.getCalories();
            totalProtein += tracking.getProtein();
            totalFat += tracking.getFat();
            totalCarbs += tracking.getCarbs();

            // Get List Nutrition Tracking Detail of user based on date and meal type => get recipe for each meal type
            List<Daily_Nutrition_Tracking_Detail> dailyNutritionTrackingDetailList =
                    dailyNutritionTrackingDetailRepository.findDaily_Nutrition_Tracking_DetailsByDaily_Nutrition_Tracking_ID(tracking.getDaily_Nutrition_Tracking_ID());

            // Logic for Meal Response to add to List of Meal Response
            MealResponse mealResponse = new MealResponse();
            mealResponse.setMealType(tracking.getMealType().toString());

            // Initial Default Value for Recipe List of Meal Response
            List<SimpleRecipeResponse> recipeForDailyTrackingResponseList = new ArrayList<>();

            // Logic for Recipe For Daily Tracking Response of Meal Response
            for (Daily_Nutrition_Tracking_Detail detail : dailyNutritionTrackingDetailList) {
                Recipe recipe = detail.getRecipe_ID();
                SimpleRecipeResponse recipeForDailyTrackingResponse = SimpleRecipeResponse.builder()
                        .recipeID(recipe.getRecipe_ID().toString())
                        .recipeName(recipe.getRecipeName())
                        .imageURL(recipe.getImageURL())
                        .calories(recipe.getTotalCalories())
                        .protein(recipe.getTotalProtein())
                        .fat(recipe.getTotalFat())
                        .carbs(recipe.getTotalCarbs())
                        .build();
                // Add Recipe to List Recipe
                recipeForDailyTrackingResponseList.add(recipeForDailyTrackingResponse);
            }
            // Add Recipe List to Meal Response
            mealResponse.setRecipeList(recipeForDailyTrackingResponseList);
            mealResponse.setDailyNutritionTrackingID(tracking.getDaily_Nutrition_Tracking_ID());

            // Add Meal Response to List Meal Response
            mealResponseList.add(mealResponse);
        }
        trackingResponseBasedOnDate.setTotalCalories(totalCalories);
        trackingResponseBasedOnDate.setTotalProtein(totalProtein);
        trackingResponseBasedOnDate.setTotalCarbs(totalCarbs);
        trackingResponseBasedOnDate.setTotalFat(totalFat);
        trackingResponseBasedOnDate.setMeals(mealResponseList);
        return trackingResponseBasedOnDate;
    }


    // --------------------------------------------------------------------------------
    // Private Method for Business Logic for Daily Nutrition Tracking
    @Transactional
    protected NutritionResponse createAndUpdateDailyMeal(DailyMealRequest request, String nutritionTrackingId) {
        validCheckerDailyMealRequest(request);
        if (nutritionTrackingId == null) {
            if (nutritionTrackingRepository.existsByUserAndDateAndMealType(request.getUser_ID(), request.getDate(), MealType.valueOf(request.getMealType()))) {
                throw new AppException(ErrorCode.DATE_WITH_MEAL_TYPE_IS_DUPLICATE);
            }
        }

        // Khởi tạo tổng giá trị dinh dưỡng
        double calories = 0;
        double protein = 0;
        double fat = 0;
        double carbs = 0;

        // Lấy thông tin người dùng từ database
        User user = userRepository.findById(request.getUser_ID())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Khởi tạo đối tượng Daily_Nutrition_Tracking
        Daily_Nutrition_Tracking dailyNutritionTracking = new Daily_Nutrition_Tracking();
        if (nutritionTrackingId != null) {
            dailyNutritionTracking = nutritionTrackingRepository.findById(nutritionTrackingId)
                    .orElseThrow(() -> new AppException(ErrorCode.NUTRITION_TRACKING_NOT_FOUND));
            if (!request.getMealType().equals(dailyNutritionTracking.getMealType().toString())) {
                throw new AppException(ErrorCode.CANT_CHANGE_MEAL_TYPE);
            }
            if (!request.getDate().equals(dailyNutritionTracking.getDate())) {
                throw new AppException(ErrorCode.CANT_CHANGE_DATE);
            }
            dailyNutritionTrackingDetailRepository.DeleteDaily_Nutrition_Tracking_DetailsByDaily_Nutrition_Tracking_ID(nutritionTrackingId);
        } else {
            UUID uuid = UUID.randomUUID();
            dailyNutritionTracking.setDaily_Nutrition_Tracking_ID(uuid.toString());
            dailyNutritionTracking.setMealType(MealType.valueOf(request.getMealType().toString()));
        }


        // Duyệt qua từng món ăn trong danh sách Recipe  to get total nutrition for this meal type
        for (String recipeID : request.getRecipeList()) {
            Recipe recipe = recipeRepository.findById(recipeID)
                    .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND));
            calories += recipe.getTotalCalories();
            protein += recipe.getTotalProtein();
            fat += recipe.getTotalFat();
            carbs += recipe.getTotalCarbs();
        }
        // Lưu đối tượng Daily_Nutrition_Tracking
        dailyNutritionTracking.setUser(user);
        dailyNutritionTracking.setDate(request.getDate());
        dailyNutritionTracking.setCalories(calories);
        dailyNutritionTracking.setProtein(protein);
        dailyNutritionTracking.setCarbs(carbs);
        dailyNutritionTracking.setFat(fat);
        dailyNutritionTracking = nutritionTrackingRepository.save(dailyNutritionTracking);


        // Logic for create Daily Nutrition Tracking Detail

        // Duyệt qua từng món ăn trong danh sách Recipe List
        for (String recipeID : request.getRecipeList()) {
            Recipe recipe = recipeRepository.findById(recipeID)
                    .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND));
            dailyNutritionTrackingDetailService.createDailyNutritionTrackingDetail(
                    DailyNutritionTrackingDetailRequest.builder()
                            .daily_Nutrition_Tracking_ID(dailyNutritionTracking.getDaily_Nutrition_Tracking_ID())
                            .recipe_ID(recipe.getRecipe_ID())
                            .build()
            );
        }
        // Response for Nutrition Tracking
        NutritionResponse nutritionResponse = toDailyNutritionResponse(dailyNutritionTracking);
        return nutritionResponse;
    }


    void validCheckerDailyMealRequest(DailyMealRequest request) {
        if (request.getRecipeList().isEmpty()) {
            throw new AppException(ErrorCode.RECIPE_LIST_IS_EMPTY);
        }
        for (String recipeID : request.getRecipeList()) {
            Recipe recipe = recipeRepository.findById(recipeID)
                    .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND));
        }
        if (request.getDate() == null) {
            throw new AppException(ErrorCode.DATE_IS_NEED_FOR_REQUEST);
        }
        try {
            var checkMealType = MealType.valueOf(request.getMealType());
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.MEAL_TYPE_IS_INVALID);
        }
    }

    // --------------------------------------------------------------------------------
    // Method to convert Daily_Nutrition_Tracking to NutritionResponse
    protected NutritionResponse toDailyNutritionResponse(Daily_Nutrition_Tracking dailyNutritionTracking) {
        NutritionResponse nutritionResponse = dailyNutritionTrackingMapper.toNutritionResponse(dailyNutritionTracking);
        nutritionResponse.setUser_ID(dailyNutritionTracking.getUser().getUser_ID());
        nutritionResponse.setMealType(dailyNutritionTracking.getMealType().toString());
        return nutritionResponse;
    }

}
