package cap2.example.Capstone2_BackEnd.NutriApp.service.authenAndAuthorService;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.authenAndAuthor.author.permission.PermissionRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.authenAndAuthor.author.permission.PermissionResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.error.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.authenAndAuthorMapper.PermissionMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Permission;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.authenAndAuthorRepository.PermissionRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.authenAndAuthorRepository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;
    RoleRepository roleRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public PermissionResponse create(PermissionRequest request) {
        Optional<Permission> permission = permissionRepository.findById(request.getName());

        if (permission.isPresent()) {
            throw new AppException(ErrorCode.PERMISSIONS_EXISTED);
        } else {
            Permission permission1 = permissionMapper.toPermission(request);
            permissionRepository.save(permission1);
            return permissionMapper.toPermissionResponse(permission1);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public PermissionResponse get(String permission) {
        var permissionEntity = permissionRepository.findById(permission).orElseThrow(() -> new AppException(ErrorCode.PERMISSIONS_NOT_FOUND));
        return permissionMapper.toPermissionResponse(permissionEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String delete(String permission) {
        if (permissionRepository.existsById(permission)) {
            permissionRepository.deleteById(permission);
            return "Delete Permission successfully";
        } else
            throw new AppException(ErrorCode.PERMISSIONS_NOT_FOUND);

    }
}
