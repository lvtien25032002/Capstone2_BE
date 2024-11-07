package cap2.example.Capstone2_BackEnd.NutriApp.mapper;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.Daily_Nutrition_Tracking.NutritionCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.Daily_Nutrition_Tracking.NutritionResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Daily_Nutrition_Tracking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Daily_Nutrition_TrackingMapper {


    @Mapping(source = "daily_Nutrition_Tracking_ID", target = "Daily_Nutrition_Tracking_ID")
    NutritionResponse toNutritionResponse(Daily_Nutrition_Tracking nutritionTracking);

    Daily_Nutrition_Tracking toDaily_Nutrition_Tracking(NutritionCreateRequest request);

    // Cấu hình cập nhật từ NutritionUpdateRequest sang Daily_Nutrition_Tracking
//    @Mapping(target = "date", source = "date")
//    @Mapping(target = "user", source = "user_ID")
//    void updateDaily_Nutrition_Tracking(@MappingTarget Daily_Nutrition_Tracking nutritionTracking, NutritionUpdateRequest request);


//    // Custom method để map từ String user_ID sang User
//    default User map(String userId) {
//        if (userId == null) {
//            return null;
//        }
//        User user = new User();
//        user.setUser_ID(userId); // Đảm bảo User có phương thức setId()
//        return user;
//    }
}
