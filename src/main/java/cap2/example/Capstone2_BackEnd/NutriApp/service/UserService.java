package cap2.example.Capstone2_BackEnd.NutriApp.service;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UpdateDietaryPreference;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.user.UserResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.DietaryPreference;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.UserMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.BaseUserRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RoleRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    private final BaseUserRepository baseUserRepository;

    //    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<UserResponse> getAllUsers() {
        var users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    ;


    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    ;

    @PostAuthorize("returnObject.username == authentication.username")
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    ;


    public UserResponse createUser(UserCreateRequest request) {
        if (baseUserRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        return userMapper.toUserResponse(userRepository.save(user));
    }

    ;

    @PostAuthorize("returnObject.username == authentication.username")
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            throw new AppException(ErrorCode.ROLES_NOT_PROVIDED);
        }
        var roles = roleRepository.findAllById(request.getRoles());
        if (roles.isEmpty()) {
            throw new AppException(ErrorCode.ROLES_NOT_FOUND);
        }
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserResponse(userRepository.save(user));
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

    @PostAuthorize("returnObject.username == authentication.username")
    public String updateDietaryPreference(String id, UpdateDietaryPreference request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        DietaryPreference validPreference;
        try {
            validPreference = DietaryPreference.valueOf(String.valueOf(request.getDietaryPreference()));
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.INVALID_DIETARY_PREFERENCE);
        }
        user.setDietaryPreference(validPreference);
        userRepository.save(user);
        return "Update dietary preference successfully";
    }

}
