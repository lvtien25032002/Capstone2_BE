package cap2.example.Capstone2_BackEnd.NutriApp.service.authenAndAuthorService;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.authentication.*;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.model.BaseUser;
import cap2.example.Capstone2_BackEnd.NutriApp.model.InvalidatedToken;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.authenAndAuthorRepository.InvalidatedTokenRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository.BaseUserRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class AuthenticationService {
    //    protected static String SIGNER_KEY = "PKqMCQ/yWaJHVrem2bWKnl+2zWlqAyYagFMkiSVlwjNR+NM0QKn+17DDwhPuFmm2";
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    BaseUserRepository baseUserRepository;

    @NonFinal
    @Value("${jwt-signerKey}")
    protected String SIGNER_KEY;
    @NonFinal
    @Value("${valid-duration}")
    protected long EXPIRATION_TIME;

    @NonFinal
    @Value("${refreshable-duraion}")
    protected long REFRESH_EXPIRATION_TIME;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Optional<BaseUser> baseUserOptional = baseUserRepository.findByUsername(request.getUsername());

        // Nếu không tìm thấy người dùng, trả về thông báo lỗi
        if (baseUserOptional.isEmpty()) {
            return AuthenticationResponse.builder()
                    .authenticated(false)
                    .build();
        }

        // Lấy đối tượng BaseUser ra từ Optional
        BaseUser baseUser = baseUserOptional.get();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        boolean authenticated = passwordEncoder.matches(request.getPassword(), baseUser.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        var token = generateToken(baseUser);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    ;


    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException, AppException {
        var token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }
        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    public void logout(LogOutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);
            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date ExpiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jit)
                    .expiryTime(ExpiryTime)
                    .build();

            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException e) {
            log.info("Token is already invalidated");
        }
    }

    ;

    private String generateToken(BaseUser baseUser) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(baseUser.getUsername())
                .issuer("nutriapp.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(EXPIRATION_TIME, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(baseUser))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Error while generating token", e);
            throw new RuntimeException("Error while generating token");
        }
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException, AppException {

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTime = (isRefresh)
                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime()
                .toInstant().plus(REFRESH_EXPIRATION_TIME, ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);
        if (!(verified && expirationTime.after(new Date()))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signToken = verifyToken(request.getToken(), true);
        var jit = signToken.getJWTClaimsSet().getJWTID();

        var expiryTime = signToken.getJWTClaimsSet().getExpirationTime();


        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);

        var user = userRepository.findByUsername(signToken.getJWTClaimsSet().getSubject()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String buildScope(BaseUser baseUser) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(baseUser.getRoles())) {
            baseUser.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> {
                        stringJoiner.add(permission.getName());
                    });
                }

            });
        }
        return stringJoiner.toString();
    }

}
