package cap2.example.Capstone2_BackEnd.NutriApp.dto.favorite.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FavoriteResponse {
    String favoriteID;
    String userID;
    String recipeID;
    LocalDate dateAdded;
}
