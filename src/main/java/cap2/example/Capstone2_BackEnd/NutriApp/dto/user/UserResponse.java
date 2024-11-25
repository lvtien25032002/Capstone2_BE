package cap2.example.Capstone2_BackEnd.NutriApp.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String User_ID;
    String username;
    String password;
    String email;
    String fullname;
    int age;
    // True = Male ; False = Female
    boolean gender;
    double weight;
    double height;
    String activityFactor;
    String nutritionPlan;
    String dietType;
}
