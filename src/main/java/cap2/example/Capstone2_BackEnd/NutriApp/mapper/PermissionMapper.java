package cap2.example.Capstone2_BackEnd.NutriApp.mapper;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.permission.PermissionRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.permission.PermissionResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}