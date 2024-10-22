package cap2.example.Capstone2_BackEnd.NutriApp.configuration;


import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        final Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dwq1whrdj");
        config.put("api_key", "649711195248914");
        config.put("api_secret", "HVgwdyATf-lMl4MmRxtQo4BsV7Y");
        return new Cloudinary(config);
    }
}
