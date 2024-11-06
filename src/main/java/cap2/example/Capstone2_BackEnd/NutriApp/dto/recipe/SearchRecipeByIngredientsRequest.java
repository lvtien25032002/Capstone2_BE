package cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchRecipeByIngredientsRequest {
    List<String> ingredients;
}
