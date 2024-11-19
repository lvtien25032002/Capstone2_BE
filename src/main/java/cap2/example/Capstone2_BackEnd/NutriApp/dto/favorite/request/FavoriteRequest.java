package cap2.example.Capstone2_BackEnd.NutriApp.dto.favorite.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FavoriteRequest {
    String userID;
    String recipeID;
}