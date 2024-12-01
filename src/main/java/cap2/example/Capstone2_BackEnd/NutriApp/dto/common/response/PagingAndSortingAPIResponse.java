package cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class PagingAndSortingAPIResponse<T> {
    int code = 1000;
    String message = "Success";
    List<T> data;
    long totalRecords;
    int totalPages;
    int pageNo;
    int pageSize;
}
