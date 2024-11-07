package cap2.example.Capstone2_BackEnd.NutriApp.controller;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.user.UpdateDietaryPreference;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.user.UserCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.user.UserUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.user.UserResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class UserController {
    UserService userService;


    @GetMapping("/all")
    public Object getAllUsers(
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String[] sort) {

        // If no pagination parameters are provided, return all users
        if ((pageNo == null && pageSize == null && sort == null) || pageNo == -1) {
            ApiResponse<List<UserResponse>> response = new ApiResponse<>();
            response.setCode(1000);
            response.setMessage("Get All Users successfully");
            response.setData(userService.getAllUsers()); // Call your existing method to get all users
            return response;
        } else {

            pageNo = pageNo - 1;
            if (pageSize == null) {
                pageSize = 10;
            }
            if (pageSize < 1) {
                throw new IllegalArgumentException("Page size must be greater than 0");
            }
            return userService.getPagingAllUsers(pageNo, pageSize, sort);
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable String id) {
        ApiResponse<UserResponse> userApiResponse = new ApiResponse<>();
        userApiResponse.setMessage("Get User successfully");
        userApiResponse.setData(userService.getUserById(id));
        return userApiResponse;
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        ApiResponse<UserResponse> userApiResponse = new ApiResponse<>();
        userApiResponse.setMessage("Get User successfully");
        userApiResponse.setData(userService.getMyInfo());
        return userApiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        ApiResponse<UserResponse> userApiResponse = new ApiResponse<>();
        try {
            userApiResponse.setMessage("Update User successfully");
            userApiResponse.setData(userService.updateUser(id, request));
            return userApiResponse;
        } catch (Exception e) {
            userApiResponse.setMessage(e.getMessage());
            return userApiResponse;
        }
    }

    @PostMapping("")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        ApiResponse<UserResponse> userApiResponse = new ApiResponse<>();
        try {
            userApiResponse.setMessage("Create User successfully");
            userApiResponse.setData(userService.createUser(request));
            return userApiResponse;
        } catch (Exception e) {
            userApiResponse.setMessage(e.getMessage());
            return userApiResponse;
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable String id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(userService.deleteUser(id));
        return apiResponse;
    }


    @PutMapping("/dietary/{id}")
    public ApiResponse<String> updateUser(@PathVariable String id, @RequestBody UpdateDietaryPreference request) {
        ApiResponse<String> userApiResponse = new ApiResponse<>();
        try {
            userApiResponse.setMessage("Update User successfully");
            userApiResponse.setData(userService.updateDietaryPreference(id, request));
            return userApiResponse;
        } catch (Exception e) {
            userApiResponse.setMessage(e.getMessage());
            return userApiResponse;
        }
    }
}
