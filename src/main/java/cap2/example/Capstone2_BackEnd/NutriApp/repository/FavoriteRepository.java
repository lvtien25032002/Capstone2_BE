package cap2.example.Capstone2_BackEnd.NutriApp.repository;

import cap2.example.Capstone2_BackEnd.NutriApp.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, String> {
    @Query("SELECT f FROM Favorite f WHERE f.User_ID.User_ID = :userID")
    List<Favorite> findByUser_ID(@Param("userID") String userID);

    @Query("SELECT r, COUNT(f) as favoriteCount " +
            "FROM Favorite f JOIN f.Recipe_ID r " +
            "GROUP BY r " +
            "ORDER BY favoriteCount DESC")
    List<Object[]> findTrendingRecipe();

    ;
}
