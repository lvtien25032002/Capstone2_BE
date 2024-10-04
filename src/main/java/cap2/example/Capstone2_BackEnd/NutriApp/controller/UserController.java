package cap2.example.Capstone2_BackEnd.NutriApp.controller;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.common.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.common.ListReponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import cap2.example.Capstone2_BackEnd.NutriApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ApiResponse<ListReponse> getAllUsers() {
        ApiResponse<ListReponse> userApiResponse = new ApiResponse<>();
        userApiResponse.setMessage("Get all users successfully");
        userApiResponse.setData(new ListReponse<>(userService.getAllUsers()));
        return userApiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable UUID id) {
        ApiResponse<User> userApiResponse = new ApiResponse<>();
        userApiResponse.setMessage("Get User successfully");
        userApiResponse.setData(userService.getUserById(id));
        return userApiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequest request) {
        ApiResponse<User> userApiResponse = new ApiResponse<>();
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
