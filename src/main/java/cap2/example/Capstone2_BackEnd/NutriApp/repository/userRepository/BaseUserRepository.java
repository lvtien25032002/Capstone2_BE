package cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository;

import cap2.example.Capstone2_BackEnd.NutriApp.model.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseUserRepository<T extends BaseUser> extends JpaRepository<T, String> {
    boolean existsByUsername(String username);

    Optional<T> findByUsername(String username);
}
