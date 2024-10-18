package cap2.example.Capstone2_BackEnd.NutriApp.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1003, "User not found", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1004, "Username must be between 6 and 20 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1005, "Password must be between 8 and 16 characters", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1006, "Email is invalid", HttpStatus.BAD_REQUEST),
    AGE_INVALID(1007, "Age must be between 10 and 100", HttpStatus.BAD_REQUEST),
    FIELD_REQUIRED(1008, " is required ", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1009, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    USER_NOT_EXISTED(1010, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1011, "You do not have permission", HttpStatus.FORBIDDEN),
    ROLES_NOT_PROVIDED(1012, "Roles not provided", HttpStatus.BAD_REQUEST),
    ROLES_NOT_FOUND(1013, "Role not found", HttpStatus.NOT_FOUND),
    ROLES_EXISTED(1014, "Roles existed", HttpStatus.BAD_REQUEST),
    INVALID_JSON(1015, "Invalid JSON", HttpStatus.BAD_REQUEST),
    PERMISSIONS_NOT_PROVIDED(1016, "Permissions not provided", HttpStatus.BAD_REQUEST),
    PERMISSIONS_NOT_FOUND(1017, "Permissions not found", HttpStatus.NOT_FOUND),
    PERMISSIONS_EXISTED(1018, "Permissions existed", HttpStatus.BAD_REQUEST),
    PERMISSIONS_IN_LIST_NOT_FOUND(1019, "One permission in list not found", HttpStatus.BAD_REQUEST),
    INVALID_DIETARY_PREFERENCE(1020, "Invalid dietary preference in the Enum", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1021, "Invalid token", HttpStatus.UNAUTHORIZED);


    private int code;
    private String message;
    private HttpStatus httpStatusCode;


    ErrorCode(int code, String message, HttpStatus httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
