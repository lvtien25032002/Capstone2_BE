package cap2.example.Capstone2_BackEnd.NutriApp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@OpenAPIDefinition(
        info = @Info(
                title = "CAPSTONE2 API",
                version = "1.0",
                description = "API for Nutri Application of Capstone2 Project of C2SE.02",
                contact = @Contact(name = "LVTien", email = "lvtien2503@gmail.com"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")
        )
)
public class Capstone2BackEndApplication {
    public static void main(String[] args) {
        SpringApplication.run(Capstone2BackEndApplication.class, args);
    }
}
