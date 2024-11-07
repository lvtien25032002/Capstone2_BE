package cap2.example.Capstone2_BackEnd.NutriApp.configuration;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.authenAndAuthor.authen.IntrospectRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.service.authenAndAuthorService.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;


@Component
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt-signerKey}")
    private String signerKey;

    @Autowired
    private AuthenticationService authenticationService;

    private NimbusJwtDecoder nimBusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            var response = authenticationService.introspect(IntrospectRequest.builder().token(token).build());
            if (!response.isValid())
                throw new AppException(ErrorCode.INVALID_TOKEN);
        } catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        }
        if (Objects.isNull(nimBusJwtDecoder)) {
            SecretKeySpec seccrectKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
            nimBusJwtDecoder = NimbusJwtDecoder
                    .withSecretKey(seccrectKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }
        return nimBusJwtDecoder.decode(token);
    }
}
