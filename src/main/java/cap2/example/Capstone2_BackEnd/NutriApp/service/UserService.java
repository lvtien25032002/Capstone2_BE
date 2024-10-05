package cap2.example.Capstone2_BackEnd.NutriApp.service;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.user.UserResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.UserMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    ;

    public UserResponse getUserById(UUID id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    ;


    public User createUser(UserCreateRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        return userRepository.save(user);
    }

    ;


    public UserResponse updateUser(UUID id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    ;


    public boolean deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    ;

}
