package cap2.example.Capstone2_BackEnd.NutriApp.service.authenAndAuthorService;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.authenAndAuthor.author.role.RoleRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.authenAndAuthor.author.role.RoleResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.authenAndAuthor.author.role.RoleUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.error.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.authenAndAuthorMapper.RoleMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Role;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.authenAndAuthorRepository.PermissionRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.authenAndAuthorRepository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleResponse> getAll() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponse get(String role) {
        var roleEntity = roleRepository.findById(role).orElseThrow(() -> new AppException(ErrorCode.ROLES_NOT_FOUND));
        return roleMapper.toRoleResponse(roleEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponse create(RoleRequest request) {
        Optional<Role> roleChecker = roleRepository.findById(request.getName());
        if (roleChecker.isPresent()) {
            throw new AppException(ErrorCode.ROLES_EXISTED);
        } else {
            var permissions = permissionRepository.findAllById(request.getPermissions());
            if (permissions.size() != request.getPermissions().size()) {
                throw new AppException(ErrorCode.PERMISSIONS_IN_LIST_NOT_FOUND);
            }
            var role = roleMapper.toRole(request);
            role.setPermissions(new HashSet<>(permissions));
            roleRepository.save(role);
            return roleMapper.toRoleResponse(role);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponse update(String role, RoleUpdateRequest request) {
        var roleEntity = roleRepository.findById(role).orElseThrow(() -> new AppException(ErrorCode.ROLES_NOT_FOUND));
        if (request.getPermissions() == null || request.getPermissions().isEmpty()) {
            throw new AppException(ErrorCode.PERMISSIONS_NOT_PROVIDED);
        }
        var permissions = permissionRepository.findAllById(request.getPermissions());
        if (permissions.size() != request.getPermissions().size()) {
            throw new AppException(ErrorCode.PERMISSIONS_IN_LIST_NOT_FOUND);
        }
        roleMapper.updateRole(roleEntity, request);
        roleEntity.setPermissions(new HashSet<>(permissions));
        return roleMapper.toRoleResponse(roleRepository.save(roleEntity));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String delete(String role) {
        if (roleRepository.existsById(role)) {
            roleRepository.deleteById(role);
            return "Delete Role successfully";
        } else
            throw new AppException(ErrorCode.ROLES_NOT_FOUND);
    }
}
