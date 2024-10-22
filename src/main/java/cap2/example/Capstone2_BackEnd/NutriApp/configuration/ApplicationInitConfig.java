package cap2.example.Capstone2_BackEnd.NutriApp.configuration;

import cap2.example.Capstone2_BackEnd.NutriApp.constant.PredefinedRole;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Admin;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Role;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.authenAndAuthorRepository.RoleRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class ApplicationInitConfig {
    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";
    PasswordEncoder passwordEncoder;


    @Bean
    ApplicationRunner applicationRunner(AdminRepository adminRepository, RoleRepository roleRepository) {
        return args -> {
            if (adminRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                roleRepository.save(Role.builder()
                        .name(PredefinedRole.USER_ROLE)
                        .description("User role")
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                        .name(PredefinedRole.ADMIN_ROLE)
                        .description("Admin role")
                        .build());
                Set<Role> roles = Set.of(roleRepository.findByName("ADMIN"));
                Admin admin = Admin.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode("12345678"))
                        .roles(roles)
                        .adminCode("C2SE.02")
                        .email("admin@gmail.com")
                        .fullname("admin")
                        .createdAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
                        .build();

                adminRepository.save(admin);
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }
}
