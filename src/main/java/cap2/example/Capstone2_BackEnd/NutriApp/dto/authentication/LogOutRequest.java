package cap2.example.Capstone2_BackEnd.NutriApp.dto.authentication;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogOutRequest {
    String token;
}
