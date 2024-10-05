package cap2.example.Capstone2_BackEnd.NutriApp.mapper;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.user.UserUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.user.UserResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
        user.setEmail( request.getEmail() );
        user.setFullname( request.getFullname() );
        user.setAge( request.getAge() );
        user.setGender( request.isGender() );

        return user;
    }

    @Override
    public void updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        user.setPassword( request.getPassword() );
        user.setEmail( request.getEmail() );
        user.setFullname( request.getFullname() );
        user.setAge( request.getAge() );
        user.setGender( request.isGender() );
        user.setWeight( request.getWeight() );
        user.setHeight( request.getHeight() );
        user.setGoal( request.getGoal() );
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.username( user.getUsername() );
        userResponse.password( user.getPassword() );
        userResponse.email( user.getEmail() );
        userResponse.fullname( user.getFullname() );
        userResponse.age( user.getAge() );
        userResponse.gender( user.isGender() );
        userResponse.weight( user.getWeight() );
        userResponse.height( user.getHeight() );
        userResponse.goal( user.getGoal() );
        userResponse.createdAt( user.getCreatedAt() );

        return userResponse.build();
    }
}
