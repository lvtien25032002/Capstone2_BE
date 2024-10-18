package cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user;

import cap2.example.Capstone2_BackEnd.NutriApp.enums.DietaryPreference;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UpdateDietaryPreference {
    DietaryPreference dietaryPreference;
}
