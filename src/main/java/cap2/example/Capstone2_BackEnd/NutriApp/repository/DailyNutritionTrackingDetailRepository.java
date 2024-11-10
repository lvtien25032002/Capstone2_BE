package cap2.example.Capstone2_BackEnd.NutriApp.repository;


import cap2.example.Capstone2_BackEnd.NutriApp.model.Daily_Nutrition_Tracking_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyNutritionTrackingDetailRepository extends JpaRepository<Daily_Nutrition_Tracking_Detail, String> {
    @Query("SELECT u FROM Daily_Nutrition_Tracking_Detail u WHERE u.daily_Nutrition_Tracking_ID.Daily_Nutrition_Tracking_ID = :daily_nutrition_tracking_id")
    List<Daily_Nutrition_Tracking_Detail> findDaily_Nutrition_Tracking_DetailsByDaily_Nutrition_Tracking_ID(@Param("daily_nutrition_tracking_id") String daily_nutrition_tracking_id);


    @Modifying
    @Query("DELETE  FROM Daily_Nutrition_Tracking_Detail r WHERE r.daily_Nutrition_Tracking_ID.Daily_Nutrition_Tracking_ID = :daily_nutrition_tracking_id")
    void DeleteDaily_Nutrition_Tracking_DetailsByDaily_Nutrition_Tracking_ID(@Param("daily_nutrition_tracking_id") String daily_nutrition_tracking_id);

}
