package cap2.example.Capstone2_BackEnd.NutriApp.dto.favorite.response;

import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrendingRecipeResponse {
    String recipeID;
    Long favoriteCount;

    public TrendingRecipeResponse(Recipe recipe, Long countRecipe) {
        this.recipeID = recipe.getRecipe_ID();
        this.favoriteCount = countRecipe;
    }


}
