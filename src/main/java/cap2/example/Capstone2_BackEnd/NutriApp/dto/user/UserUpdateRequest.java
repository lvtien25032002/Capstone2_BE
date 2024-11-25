package cap2.example.Capstone2_BackEnd.NutriApp.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @NotEmpty(message = "Password is required")
    @Size(min = 8, max = 20, message = "PASSWORD_INVALID")
    String password;
    @NotEmpty(message = "EMAIL_REQUIRED")
    String email;
    @NotEmpty(message = "Fullname is required")
    String fullname;
    @NotNull(message = "Age is required")
    @Min(value = 10, message = "AGE_INVALID")
    @Max(value = 100, message = "AGE_INVALID")
    Integer age;
    @NotNull(message = "Gender is required")
    Boolean gender;
    @NotNull(message = "Weight is required")
    Double weight;
    @NotNull(message = "Height is required")
    Double height;
    @NotEmpty(message = "Activity Factor is required")
    String activityFactor;
    @NotEmpty(message = "Nutrition Plan is required")
    String nutritionPlan;
    @NotEmpty(message = "dietType is required")
    String dietType;
}
