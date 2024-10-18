package cap2.example.Capstone2_BackEnd.NutriApp.mapper;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.role.RoleRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.role.RoleUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.role.RoleResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    @Mapping(target = "permissions", ignore = true)
    void updateRole(@MappingTarget Role role, RoleUpdateRequest request);


    RoleResponse toRoleResponse(Role role);
}