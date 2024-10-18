package cap2.example.Capstone2_BackEnd.NutriApp.dto.response.permission;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class PermissionResponse {
    String name;
    String description;
}
