package cap2.example.Capstone2_BackEnd.NutriApp.mapper;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.user.UserResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Mapper functions for User Request
    User toUser(UserCreateRequest request);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    // Mapper functions for User Response
    UserResponse toUserResponse(User user);
}
