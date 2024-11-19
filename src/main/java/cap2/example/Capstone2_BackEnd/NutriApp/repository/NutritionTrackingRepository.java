package cap2.example.Capstone2_BackEnd.NutriApp.repository;

import cap2.example.Capstone2_BackEnd.NutriApp.enums.MealType;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Daily_Nutrition_Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NutritionTrackingRepository extends JpaRepository<Daily_Nutrition_Tracking, String> {

    @Query("SELECT COUNT(u) > 0 FROM Daily_Nutrition_Tracking u WHERE  u.User.User_ID  = :user_id AND u.date = :date AND u.mealType = :mealType")
    boolean existsByUserAndDateAndMealType(@Param("user_id") String user_id, @Param("date") LocalDate date, @Param("mealType") MealType mealType);

    @Query("SELECT u FROM Daily_Nutrition_Tracking u WHERE  u.User.User_ID  = :user_id AND u.date = :date ")
    List<Daily_Nutrition_Tracking> findDaily_Nutrition_TrackingByUserAndDate(@Param("user_id") String user_id, @Param("date") LocalDate date);

    @Query("SELECT u FROM Daily_Nutrition_Tracking u WHERE  u.User.User_ID  = :user_id ")
    List<Daily_Nutrition_Tracking> findDaily_Nutrition_TrackingByUser(@Param("user_id") String user_id);
}
