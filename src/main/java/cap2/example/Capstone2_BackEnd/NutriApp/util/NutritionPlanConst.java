package cap2.example.Capstone2_BackEnd.NutriApp.util;


import cap2.example.Capstone2_BackEnd.NutriApp.enums.recipe.NutritionalQuality;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.NutritionPlan;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NutritionPlanConst {
    public static final Map<String, NutritionalQuality> NUTRITION_PLAN_MAP;

    // Static block để khởi tạo map
    static {
        Map<String, NutritionalQuality> map = new HashMap<>();
        map.put(NutritionPlan.DURABLE.name(), NutritionalQuality.BALANCED);
        map.put(NutritionPlan.HIGH_PROTEIN.name(), NutritionalQuality.HIGH_PROTEIN);
        map.put(NutritionPlan.LOW_CARB.name(), NutritionalQuality.LOW_CARB);
        map.put(NutritionPlan.BALANCED.name(), NutritionalQuality.BALANCED);
        map.put(NutritionPlan.AFTER_WEIGHT_LOSS.name(), NutritionalQuality.LOW_CALORIE);
        map.put(NutritionPlan.MAINTAIN_MUSCLES.name(), NutritionalQuality.HIGH_PROTEIN);
        map.put(NutritionPlan.HIGH_ENERGY.name(), NutritionalQuality.HIGH_CALORIE);
        map.put(NutritionPlan.BUILD_MUSCLE.name(), NutritionalQuality.HIGH_PROTEIN);
        map.put(NutritionPlan.ATHLETE.name(), NutritionalQuality.HIGH_PROTEIN);
        map.put(NutritionPlan.CARDIO_TRAINING.name(), NutritionalQuality.BALANCED);

        // Đóng băng map để không thay đổi
        NUTRITION_PLAN_MAP = Collections.unmodifiableMap(map);
    }
}
