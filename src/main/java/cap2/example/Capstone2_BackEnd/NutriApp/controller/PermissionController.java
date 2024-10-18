package cap2.example.Capstone2_BackEnd.NutriApp.controller;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.permission.PermissionRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.permission.PermissionResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        ApiResponse<PermissionResponse> permissionResponseApiResponse = new ApiResponse<>();
        permissionResponseApiResponse.setMessage("Create permission successfully");
        permissionResponseApiResponse.setData(permissionService.create(request));
        return permissionResponseApiResponse;
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getALl() {
        ApiResponse<List<PermissionResponse>> permissionResponseApiResponse = new ApiResponse<>();
        permissionResponseApiResponse.setMessage("Get all permissions successfully");
        permissionResponseApiResponse.setData(permissionService.getAll());
        return permissionResponseApiResponse;
    }

    @GetMapping("/{permission}")
    ApiResponse<PermissionResponse> get(@PathVariable String permission) {
        ApiResponse<PermissionResponse> permissionResponseApiResponse = new ApiResponse<>();
        permissionResponseApiResponse.setMessage("Get permission successfully");
        permissionResponseApiResponse.setData(permissionService.get(permission));
        return permissionResponseApiResponse;
    }

    @DeleteMapping("/{permission}")
    ApiResponse<String> delete(@PathVariable String permission) {
        ApiResponse<String> permissionResponseApiResponse = new ApiResponse<>();
        permissionResponseApiResponse.setMessage(permissionService.delete(permission));
        return permissionResponseApiResponse;
    }
}
