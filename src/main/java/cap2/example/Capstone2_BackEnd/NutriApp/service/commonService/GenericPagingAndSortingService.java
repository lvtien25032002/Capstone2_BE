package cap2.example.Capstone2_BackEnd.NutriApp.service.commonService;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.PagingAndSortingAPIResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class GenericPagingAndSortingService<T> {

    JpaRepository<T, Long> repository;

    public PagingAndSortingAPIResponse<T> getPage(int page, int size, String[] sort) {
        if (size > 100) {
            size = 100; // Limit page size to 100
        }
        if (sort != null) {
            if (sort.length > 2) {
                throw new IllegalArgumentException("Sort parameter must be in the format: sort=field,asc or sort=field,desc");
            }
        }
        Pageable pageable = createPageable(page, size, sort);
        Page<T> resultPage = repository.findAll(pageable);
        List<T> data = resultPage.getContent().stream().toList();

        return PagingAndSortingAPIResponse.<T>builder()
                .data(data)
                .totalRecords(resultPage.getTotalElements())
                .totalPages(resultPage.getTotalPages())
                .pageNo(resultPage.getNumber() + 1)
                .pageSize(resultPage.getSize())
                .build();
    }


    public Pageable createPageable(int page, int size, String[] sort) {
        if (sort == null || sort.length == 0) {
            // Return pageable without sorting if sort is null or empty
            return PageRequest.of(page, size);
        }

        // Handle sorting if sort is provided
        String sortField = sort[0];
        Sort.Direction direction = Sort.Direction.fromString(sort.length > 1 ? sort[1] : "asc");
        return PageRequest.of(page, size, Sort.by(direction, sortField));
    }
}