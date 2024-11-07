package cap2.example.Capstone2_BackEnd.NutriApp.repository;

import cap2.example.Capstone2_BackEnd.NutriApp.model.Daily_Nutrition_Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface NutritionTrackingRepository extends JpaRepository<Daily_Nutrition_Tracking, String> {

    @Query("SELECT COUNT(u) > 0 FROM Daily_Nutrition_Tracking u WHERE  u.User.User_ID  = :user_id AND u.date = :date ")
    boolean existsByUserAndDate(@Param("user_id") String user_id, @Param("date") LocalDate date);
}
