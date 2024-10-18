package cap2.example.Capstone2_BackEnd.NutriApp.controller;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.role.RoleRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.role.RoleUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.role.RoleResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        ApiResponse<RoleResponse> roleResponseApiResponse = new ApiResponse<>();
        roleResponseApiResponse.setMessage("Create role successfully");
        roleResponseApiResponse.setData(roleService.create(request));
        return roleResponseApiResponse;
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getALl() {
        ApiResponse<List<RoleResponse>> roleResponseApiResponse = new ApiResponse<>();
        roleResponseApiResponse.setMessage("Get all roles successfully");
        roleResponseApiResponse.setData(roleService.getAll());
        return roleResponseApiResponse;
    }

    @GetMapping("/{role}")
    ApiResponse<RoleResponse> get(@PathVariable String role) {
        ApiResponse<RoleResponse> roleResponseApiResponse = new ApiResponse<>();
        roleResponseApiResponse.setMessage("Get role successfully");
        roleResponseApiResponse.setData(roleService.get(role));
        return roleResponseApiResponse;
    }

    @PutMapping("/{role}")
    ApiResponse<RoleResponse> update(@PathVariable String role, @RequestBody RoleUpdateRequest request) {
        log.info("Updated role Controller: {}", request.getPermissions());
        log.info("Path variable: {}", role);
        ApiResponse<RoleResponse> roleResponseApiResponse = new ApiResponse<>();
        roleResponseApiResponse.setMessage("Update role successfully");
        roleResponseApiResponse.setData(roleService.update(role, request));
        return roleResponseApiResponse;
    }

    @DeleteMapping("/{role}")
    ApiResponse<String> delete(@PathVariable String role) {
        ApiResponse<String> roleResponseApiResponse = new ApiResponse<>();
        roleResponseApiResponse.setMessage("Delete role successfully");
        roleService.delete(role);
        return roleResponseApiResponse;
    }
}
