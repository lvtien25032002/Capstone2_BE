package cap2.example.Capstone2_BackEnd.NutriApp.controller;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.ListReponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UpdateDietaryPreference;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.user.UserResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class UserController {
    UserService userService;


    @GetMapping("/all")
    public ApiResponse<ListReponse> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        ApiResponse<ListReponse> userApiResponse = new ApiResponse<>();
        userApiResponse.setMessage("Get all users successfully");
        userApiResponse.setData(new ListReponse<>(userService.getAllUsers()));
        return userApiResponse;
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
