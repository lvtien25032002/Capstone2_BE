package cap2.example.Capstone2_BackEnd.NutriApp.service;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.PagingAndSortingAPIResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.user.UserCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.user.UserResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.user.UserUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.error.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.ActivityFactor;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.DietType;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.NutritionPlan;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.userMapper.UserMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Role;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.authenAndAuthorRepository.RoleRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository.BaseUserRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository.UserRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.service.commonService.GenericPagingAndSortingService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    BaseUserRepository baseUserRepository;
    GenericPagingAndSortingService genericPagingAndSortingService;


    public PagingAndSortingAPIResponse<UserResponse> getPagingAllUsers(int page, int size, String[] sort) {
        List<User> users = userRepository.findAll();
        List<UserResponse> usersResponse = users.stream().map(user -> toUserResponse(user)).toList();
        PagingAndSortingAPIResponse<UserResponse> response = genericPagingAndSortingService.getPagingResponse(usersResponse, page, size, sort);
        return response;
    }

    //    @PreAuthorize("hasAuthority('APPROVE_POST')")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        var users = userRepository.findAll();
        return users.stream().map(user -> toUserResponse(user)).toList();
    }

    ;

    public UserResponse getUserById(String id) {
        return toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    ;

    @PostAuthorize("returnObject.username == authentication.username")
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return toUserResponse(user);
    }

    ;


    public UserResponse createUser(UserCreateRequest request) {
        if (baseUserRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        Set<Role> roles = Set.of(roleRepository.findByName("USER"));
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        user.setRoles(roles);
        user.setActive(true);
        return toUserResponse(userRepository.save(user));
    }

    ;

    //
//        @PostAuthorize("returnObject.username == authentication.username")
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (request.getIsActive() == null) {
            request.setIsActive(user.isActive());
        }
        if (user.isActive() == false && request.getIsActive() != true) {
            throw new AppException(ErrorCode.USER_NOT_ACTIVE);
        }
        if (user.isActive() != request.getIsActive()) {
            user.setActive(request.getIsActive());
            userRepository.save(user);
            return toUserResponse(user);
        }
        // Update user information
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getFullname() != null) {
            user.setFullname(request.getFullname());
        }
        if (request.getAge() != null)
            if (request.getAge() != 0) {
                user.setAge(request.getAge());
            }
        if (request.getGender() != null) {
            user.setGender(request.getGender());
        }
        if (request.getWeight() != null)
            if (request.getWeight() != 0) {
                user.setWeight(request.getWeight());
            }
        if (request.getHeight() != null)
            if (request.getHeight() != 0) {
                user.setHeight(request.getHeight());
            }
        if (request.getActivityFactor() != null) {
            user.setActivityFactor(ActivityFactor.valueOf(request.getActivityFactor()));
        }
        if (request.getDietType() != null) {
            user.setDietType(DietType.valueOf(request.getDietType()));
        }
        if (request.getNutritionPlan() != null) {
            user.setNutritionPlan(NutritionPlan.valueOf(request.getNutritionPlan()));
        }
        return toUserResponse(userRepository.save(user));
    }

    ;

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "Delete user successfully";
        } else
            throw new AppException(ErrorCode.USER_NOT_FOUND);
    }

    ;

    // ----------------------------------------------
    // Private Methods for Business Logic
    // ----------------------------------------------
    public UserResponse toUserResponse(User user) {
        UserResponse response = userMapper.toUserResponse(user);
        response.setActive(user.isActive());
        return response;
    }
//

}
