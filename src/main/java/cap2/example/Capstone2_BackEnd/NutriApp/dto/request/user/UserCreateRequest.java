package cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min = 8, max = 20, message = "USERNAME_INVALID")
    @NotEmpty(message = "Username is required")
    String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, max = 20, message = "PASSWORD_INVALID")
    String password;

    @NotEmpty(message = "EMAIL_INVALID")
    String email;
    @NotEmpty(message = "Fullname is required")
    String fullname;

//    @Min(value = 10, message = "AGE_INVALID")
//    @Max(value = 100, message = "AGE_INVALID")
//    int age;
//    boolean gender;
//    Set<String> roles;

}
