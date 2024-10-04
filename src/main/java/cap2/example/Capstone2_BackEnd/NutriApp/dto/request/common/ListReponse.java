package cap2.example.Capstone2_BackEnd.NutriApp.dto.request.common;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ListReponse<T> {
    List<T> data;
}
