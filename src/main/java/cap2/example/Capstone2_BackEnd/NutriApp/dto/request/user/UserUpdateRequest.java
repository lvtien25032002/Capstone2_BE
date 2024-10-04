package cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "EMAIL_INVALID")
    String email;

    @NotEmpty(message = "Fullname is required")
    String fullname;

    @NotEmpty(message = "Age is required")
    @Min(value = 10, message = "AGE_INVALID")
    @Max(value = 100, message = "AGE_INVALID")
    int age;
    boolean gender;
    @NotEmpty(message = "weight is required")
    double weight;
    @NotEmpty(message = "height is required")
    double height;
    String goal;
}
