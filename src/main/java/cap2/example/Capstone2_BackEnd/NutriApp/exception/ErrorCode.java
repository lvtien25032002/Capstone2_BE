package cap2.example.Capstone2_BackEnd.NutriApp.exception;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXISTED(1002, "User existed"),
    USER_NOT_FOUND(1003, "User not found"),
    USERNAME_INVALID(1004, "Username must be between 6 and 20 characters"),
    PASSWORD_INVALID(1005, "Password must be between 8 and 16 characters"),
    EMAIL_INVALID(1006, "Email is invalid"),
    AGE_INVALID(1007, "Age must be between 10 and 100"),
    UNAUTHENTICATED(1008, "Unauthenticated"),

    INGREDIENT_NAME_INVALID(2001, "Ingredient name must be between 2 and 50 characters"),
    INGREDIENT_NAME_REQUIRED(2002, "Ingredient name is required"),
    INGREDIENT_TYPE_REQUIRED(2003, "Ingredient type is required"),
    INGREDIENT_CALORIES_REQUIRED(2004, "Calories are required"),
    INGREDIENT_PROTEIN_REQUIRED(2005, "Protein is required"),
    INGREDIENT_FAT_REQUIRED(2006, "Fat is required"),
    INGREDIENT_CARBS_REQUIRED(2007, "Carbohydrates are required"),
    INGREDIENT_EXIST(2007, "Ingredient exist"),
    INGREDIENT_NOT_FOUND(2008, "Ingredient not found"),


    RECIPE_NAME_INVALID(3001, "Recipe name must be between 5 and 100 characters"),
    RECIPE_NAME_REQUIRED(3002, "Recipe name is required"),
    RECIPE_COOKING_INSTRUCTIONS_INVALID(3003, "Cooking instructions must be between 10 and 200 characters"),
    RECIPE_TOTAL_CALORIES_REQUIRED(3004, "Total calories are required"),
    RECIPE_TOTAL_PROTEIN_REQUIRED(3005, "Total protein is required"),
    RECIPE_TOTAL_CARBS_REQUIRED(3006, "Total carbohydrates are required"),
    RECIPE_TOTAL_FAT_REQUIRED(3007, "Total fat is required"),
    RECIPE_EXIST(3008, "Recipe exist"),
    RECIPE_NOT_FOUND(3009, "Recipe not found"),
    ;
    int code;
    String message;

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
