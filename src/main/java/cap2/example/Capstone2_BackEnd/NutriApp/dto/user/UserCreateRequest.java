package cap2.example.Capstone2_BackEnd.NutriApp.dto.user;

import jakarta.validation.constraints.NotEmpty;
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
    String username;
    String password;
    @NotEmpty(message = "EMAIL_REQUIRED")
    String email;
    @NotEmpty(message = "Fullname is required")
    String fullname;
}
