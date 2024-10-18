package cap2.example.Capstone2_BackEnd.NutriApp.configuration;


import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

//    @Bean
//    ApplicationRunner applicationRunner(AdminRepository adminRepository) {
//        return args -> {
//            if (adminRepository.findByUsername("admin").isEmpty()) {
//                var roles = new HashSet<Role>();
//                roles.add(Role.ADMIN);
//
//
//                Admin admin = Admin.builder()
//                        .username("admin")
//                        .password(passwordEncoder.encode("12345678"))
////                        .roles(roles)
//                        .adminCode("C2SE.02") ")
//                        .build();
//
//                adminRepository.save(admin);
//                log.warn("admin user has been created with default password: 12345678, please change it");
//            }
//        };
//    }
}
