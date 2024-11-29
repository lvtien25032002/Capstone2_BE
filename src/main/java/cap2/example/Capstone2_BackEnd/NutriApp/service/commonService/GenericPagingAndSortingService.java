package cap2.example.Capstone2_BackEnd.NutriApp.service.commonService;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.PagingAndSortingAPIResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.error.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class GenericPagingAndSortingService {
    public <T> PagingAndSortingAPIResponse<T> getPagingResponse(List<T> list, int page, int size, String[] sort) {
        if (page < 0) {
            throw new AppException(ErrorCode.PAGE_NUMBER_INVALID);
        }
        if (size > 100) {
            size = 100; // Giới hạn kích thước trang
        }
        // Sắp xếp danh sách nếu có yêu cầu
        List<T> sortedList = sortList(list, sort);

        // page here is 0-based index(In list). Plus 1 to make it 1-based index(In API)
        if ((page + 1) > (int) Math.ceil((double) sortedList.size() / size)) {
            throw new AppException(ErrorCode.PAGE_NUMBER_HIGHER_THAN_TOTAL_PAGES);
        }
        // Áp dụng paging
        int fromIndex = Math.min(page * size, sortedList.size());
        int toIndex = Math.min(fromIndex + size, sortedList.size());
        List<T> pageData = sortedList.subList(fromIndex, toIndex);

        return PagingAndSortingAPIResponse.<T>builder()
                .data(pageData)
                .totalRecords(sortedList.size())
                .totalPages((int) Math.ceil((double) sortedList.size() / size))
                .pageNo(page + 1)
                .pageSize(size)
                .build();
    }

    private <T> List<T> sortList(List<T> list, String[] sort) {
        if (sort == null || sort.length == 0) {
            return list; // Không sắp xếp nếu không có thông tin sort
        }
        // Ví dụ sắp xếp nếu đối tượng có getter phù hợp (sử dụng Reflection)
        String sortField = sort[0];
        boolean isAsc = sort.length <= 1 || "asc".equalsIgnoreCase(sort[1]);
        Comparator<T> comparator = (o1, o2) -> {
            try {
                Object value1 = getValue(o1, sortField);
                Object value2 = getValue(o2, sortField);
                @SuppressWarnings("unchecked")
                Comparable<Object> comp1 = (Comparable<Object>) value1;
                Comparable<Object> comp2 = (Comparable<Object>) value2;

                return isAsc ? comp1.compareTo(comp2) : comp2.compareTo(comp1);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid sort field: " + sortField);
            }
        };
        return list.stream().sorted(comparator).toList();
    }

    private Object getValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}


//    public PagingAndSortingAPIResponse<T> getPage(int page, int size, String[] sort) {
//        if (size > 100) {
//            size = 100; // Limit page size to 100
//        }
//        if (sort != null) {
//            if (sort.length > 2) {
//                throw new IllegalArgumentException("Sort parameter must be in the format: sort=field,asc or sort=field,desc");
//            }
//        }
//        Pageable pageable = createPageable(page, size, sort);
//        Page<T> resultPage = repository.findAll(pageable);
//        List<T> data = resultPage.getContent().stream().toList();
//
//        return PagingAndSortingAPIResponse.<T>builder()
//                .data(data)
//                .totalRecords(resultPage.getTotalElements())
//                .totalPages(resultPage.getTotalPages())
//                .pageNo(resultPage.getNumber() + 1)
//                .pageSize(resultPage.getSize())
//                .build();
//    }


//    public Pageable createPageable(int page, int size, String[] sort) {
//        if (sort == null || sort.length == 0) {
//            // Return pageable without sorting if sort is null or empty
//            return PageRequest.of(page, size);
//        }
//
//        // Handle sorting if sort is provided
//        String sortField = sort[0];
//        Sort.Direction direction = Sort.Direction.fromString(sort.length > 1 ? sort[1] : "asc");
//        return PageRequest.of(page, size, Sort.by(direction, sortField));
//    }