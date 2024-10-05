package cap2.example.Capstone2_BackEnd.NutriApp.controller;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.ListReponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.user.UserResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import cap2.example.Capstone2_BackEnd.NutriApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class UserController {
    UserService userService;


    @GetMapping("/all")
    public ApiResponse<ListReponse> getAllUsers() {
        ApiResponse<ListReponse> userApiResponse = new ApiResponse<>();
        userApiResponse.setMessage("Get all users successfully");
        userApiResponse.setData(new ListReponse<>(userService.getAllUsers()));
        return userApiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable UUID id) {
        ApiResponse<UserResponse> userApiResponse = new ApiResponse<>();
        userApiResponse.setMessage("Get User successfully");
        userApiResponse.setData(userService.getUserById(id));
        return userApiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequest request) {
        ApiResponse<UserResponse> userApiResponse = new ApiResponse<>();
        try {
            userApiResponse.setMessage("Update User successfully");
            userApiResponse.setData(userService.updateUser(id, request));
            return userApiResponse;
        } catch (Exception e) {
            userApiResponse.setMessage(e.getMessage());
            userApiResponse.setCode(1001);
            return userApiResponse;
        }
    }

    @PostMapping("")
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreateRequest request) {
        ApiResponse<User> userApiResponse = new ApiResponse<>();
        try {
            userApiResponse.setMessage("Create User successfully");
            userApiResponse.setData(userService.createUser(request));
            return userApiResponse;
        } catch (Exception e) {
            userApiResponse.setMessage(e.getMessage());
            userApiResponse.setCode(1001);
            return userApiResponse;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("Delete User successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
