package cap2.example.Capstone2_BackEnd.NutriApp.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXISTED(1002, "User existed"),
    USER_NOT_FOUND(1003, "User not found"),
    USERNAME_INVALID(1004, "Username must be between 6 and 20 characters"),
    PASSWORD_INVALID(1005, "Password must be between 8 and 16 characters"),
    EMAIL_INVALID(1006, "Email is invalid"),
    AGE_INVALID(1007, "Age must be between 10 and 100"),
    FIELD_REQUIRED(1008, " is required "),
    UNAUTHENTICATED(1009, "Unauthenticated"),
    ;


    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
