package cap2.example.Capstone2_BackEnd.NutriApp.dto.user;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.authenAndAuthor.author.role.RoleResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;


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
    boolean gender;
    double weight;
    double height;
    String goal;
    LocalDateTime createdAt;
    Set<RoleResponse> roles;
}
