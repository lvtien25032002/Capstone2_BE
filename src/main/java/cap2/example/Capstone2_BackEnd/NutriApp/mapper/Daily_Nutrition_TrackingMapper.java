package cap2.example.Capstone2_BackEnd.NutriApp.mapper;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.request.DailyNutritionRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.Daily_Nutrition_Tracking.response.NutritionResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Daily_Nutrition_Tracking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface Daily_Nutrition_TrackingMapper {


    @Mapping(source = "daily_Nutrition_Tracking_ID", target = "Daily_Nutrition_Tracking_ID")
    NutritionResponse toNutritionResponse(Daily_Nutrition_Tracking dailyNutritionTracking);


    void updateDaily_Nutrition_Tracking(@MappingTarget Daily_Nutrition_Tracking dailyNutritionTracking, DailyNutritionRequest request);
}
