package cap2.example.Capstone2_BackEnd.NutriApp.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserResponse {
    String username;
    String password;
    String email;
    String fullname;
    int age;
    boolean gender;
    double weight;
    double height;
    String goal;
    LocalDateTime createdAt;
}
