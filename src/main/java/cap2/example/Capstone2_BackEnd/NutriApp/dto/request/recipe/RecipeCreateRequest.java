package cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class RecipeCreateRequest {
    @NotEmpty(message = "RECIPE_NAME_REQUIRED")
    String recipeName;

    String description;
    @NotEmpty(message = "RECIPE_TOTAL_CALORIES_REQUIRED" )
    String cookingInstructions;

    @NotNull(message = "RECIPE_TOTAL_CALORIES_REQUIRED")
    Double totalCalories;
    @NotNull(message = "RECIPE_TOTAL_PROTEIN_REQUIRED")
    Double totalProtein;
    @NotNull(message = "RECIPE_TOTAL_CARBS_REQUIRED")
    Double totalCarbs;
    @NotNull(message = "RECIPE_TOTAL_FAT_REQUIRED")
    Double totalFat;


}
