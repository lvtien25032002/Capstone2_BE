package cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleRecipeResponse {
    String recipeID;
    String recipeName;
    String imageURL;
    int calories;
    int protein;
    int carbs;
    int fat;
}