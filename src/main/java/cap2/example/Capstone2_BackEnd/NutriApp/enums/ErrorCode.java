package cap2.example.Capstone2_BackEnd.NutriApp.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {
    // Common Errors
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    INVALID_PROPERTY(1002, "Invalid property", HttpStatus.BAD_REQUEST),

    // User Errors

    USER_EXISTED(1003, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1004, "User not found", HttpStatus.NOT_FOUND),
    PASSWORD_INVALID(1005, "Password must be between 8 and 16 characters", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1006, "Email is invalid", HttpStatus.BAD_REQUEST),
    AGE_INVALID(1007, "Age must be between 10 and 100", HttpStatus.BAD_REQUEST),
    FIELD_REQUIRED(1008, " is required ", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1010, "User not existed", HttpStatus.NOT_FOUND),

    // Authorization Errors and Permission Errors
    UNAUTHORIZED(1011, "You do not have permission", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(1009, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    ROLES_NOT_PROVIDED(1012, "Roles not provided", HttpStatus.BAD_REQUEST),
    ROLES_NOT_FOUND(1013, "Role not found", HttpStatus.NOT_FOUND),
    ROLES_EXISTED(1014, "Roles existed", HttpStatus.BAD_REQUEST),
    INVALID_JSON(1015, "Invalid JSON", HttpStatus.BAD_REQUEST),
    PERMISSIONS_NOT_PROVIDED(1016, "Permissions not provided", HttpStatus.BAD_REQUEST),
    PERMISSIONS_NOT_FOUND(1017, "Permissions not found", HttpStatus.NOT_FOUND),
    PERMISSIONS_EXISTED(1018, "Permissions existed", HttpStatus.BAD_REQUEST),
    PERMISSIONS_IN_LIST_NOT_FOUND(1019, "One permission in list not found", HttpStatus.BAD_REQUEST),
    INVALID_DIETARY_PREFERENCE(1020, "Invalid dietary preference in the Enum", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1021, "Invalid token", HttpStatus.UNAUTHORIZED),

    // File Errors

    FILE_SIZE_EXCEEDED(1022, "File size exceeded", HttpStatus.BAD_REQUEST),
    FILE_TYPE_NOT_ALLOWED(1023, "File type not allowed. Only jpg,obg,gif,bmp files", HttpStatus.BAD_REQUEST),
    UPLOAD_IMAGE_FAILED(1024, "Upload image failed", HttpStatus.BAD_REQUEST),
    DELETE_IMAGE_FAILED(1025, "Delete image failed", HttpStatus.BAD_REQUEST),
    IMAGE_NOT_FOUND(1026, "Image not found", HttpStatus.NOT_FOUND),
    // Ingredient Errors

    INGREDIENT_NAME_INVALID(2001, "Ingredient name must be between 2 and 50 characters", HttpStatus.BAD_REQUEST),
    INGREDIENT_NAME_REQUIRED(2002, "Ingredient name is required", HttpStatus.BAD_REQUEST),
    INGREDIENT_TYPE_REQUIRED(2003, "Ingredient type is required", HttpStatus.BAD_REQUEST),
    INGREDIENT_CALORIES_REQUIRED(2004, "Calories are required", HttpStatus.BAD_REQUEST),
    INGREDIENT_PROTEIN_REQUIRED(2005, "Protein is required", HttpStatus.BAD_REQUEST),
    INGREDIENT_FAT_REQUIRED(2006, "Fat is required", HttpStatus.BAD_REQUEST),
    INGREDIENT_CARBS_REQUIRED(2007, "Carbohydrates are required", HttpStatus.BAD_REQUEST),
    INGREDIENT_EXIST(2007, "Ingredient exist", HttpStatus.BAD_REQUEST),
    INGREDIENT_NOT_FOUND(2008, "Ingredient not found", HttpStatus.NOT_FOUND),
    INGREDIENT_IN_LIST_NOT_FOUND(2009, "Ingredient in list of request not found", HttpStatus.BAD_REQUEST),
    INGREDIENT_IN_LIST_NOT_NULL(2010, "Ingredient in list of request must not be null", HttpStatus.BAD_REQUEST),
    INGREDIENT_LIST_NOT_NULL(2011, "Ingredient list must not be null", HttpStatus.BAD_REQUEST),

    // Recipe Errors
    RECIPE_NAME_INVALID(3001, "Recipe name must be between 5 and 100 characters", HttpStatus.BAD_REQUEST),
    RECIPE_NAME_REQUIRED(3002, "Recipe name is required", HttpStatus.BAD_REQUEST),
    RECIPE_COOKING_INSTRUCTIONS_INVALID(3003, "Cooking instructions must be between 10 and 200 characters", HttpStatus.BAD_REQUEST),
    RECIPE_EXIST(3008, "Recipe exist", HttpStatus.BAD_REQUEST),
    RECIPE_NOT_FOUND(3009, "Recipe not found", HttpStatus.NOT_FOUND),

    // Recipe Ingredient Errors
    RECIPE_INGREDIENT_NOT_FOUND(3020, "Recipe Ingredient not found", HttpStatus.NOT_FOUND);


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
