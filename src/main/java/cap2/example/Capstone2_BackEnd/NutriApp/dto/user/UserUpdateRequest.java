package cap2.example.Capstone2_BackEnd.NutriApp.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String email;
    String fullname;
    Integer age;
    Boolean gender;
    Double weight;
    Double height;
    String activityFactor;
    String nutritionPlan;
    String dietType;
    Boolean isActive;
}
