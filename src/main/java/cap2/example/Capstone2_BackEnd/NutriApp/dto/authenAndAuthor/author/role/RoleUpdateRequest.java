package cap2.example.Capstone2_BackEnd.NutriApp.dto.authenAndAuthor.author.role;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RoleUpdateRequest {
    String description;
    List<String> permissions;
}
