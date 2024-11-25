package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.PagingAndSortingAPIResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.nutritionalCalculation.NutritionalCalculationResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.request.RecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.request.SearchRecipeByIngredientsRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.response.MealResponseForNutritionPlan;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.response.RecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.response.RecipeResponseBaseOnNutritionPlan;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.response.SimpleRecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.IngredientForRecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.IngredientForRecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.error.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.recipe.DifficultyLevel;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.recipe.MealType;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.recipe.NutritionalQuality;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.user.DietType;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.RecipeMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Ingredient;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe_Ingredient;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.IngredientRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeIngredientRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository.UserRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.service.commonService.GenericPagingAndSortingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeService {
    RecipeRepository recipeRepository;
    IngredientRepository ingredientRepository;
    RecipeMapper recipeMapper;
    RecipeIngredientService recipeIngredientService;
    RecipeIngredientRepository recipeIngredientRepository;
    GenericPagingAndSortingService genericPagingAndSortingService;
    private final UserRepository userRepository;
    private final NutritionalCalculationService nutritionalCalculationService;


    public PagingAndSortingAPIResponse<RecipeResponse> getPagingAllRecipes(int page, int size, String[] sort) {
        Page<Recipe> recipes = recipeRepository.findAll(genericPagingAndSortingService.createPageable(page, size, sort));
        List<RecipeResponse> recipeResponses = recipes.map(recipe -> {
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();

        return PagingAndSortingAPIResponse.<RecipeResponse>builder()
                .message("Success")
                .data(recipeResponses)
                .totalRecords(recipes.getTotalElements())
                .totalPages(recipes.getTotalPages())
                .pageNo(recipes.getNumber() + 1)
                .pageSize(recipes.getSize())
                .build();
    }


    public List<RecipeResponse> getAllRecipes() {
        return recipeRepository.findAll().stream().map(recipe -> {
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            // Logic for Ingredient Response
            recipeResponse.setIngredientList(recipeIngredientService.getIngredientsForRecipeResponse(recipe));

            // Logic for MealType Response
            recipeResponse.setMealType(setMealTypeResponse(recipe.getMealType()));
            return recipeResponse;
        }).toList();

    }

    @Transactional
    public RecipeResponse createRecipe(RecipeRequest request) {
        if (recipeRepository.existsByRecipeName(request.getRecipeName())) {
            throw new AppException(ErrorCode.RECIPE_EXIST);
        }
        return setRecipeToSaveAndResponse(request, null);
    }


    public RecipeResponse getRecipe(String id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND));

        //Logic for Ingredient Response
        RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
        recipeResponse.setIngredientList(recipeIngredientService.getIngredientsForRecipeResponse(recipe));

        //Logic for MealType Response
        recipeResponse.setMealType(setMealTypeResponse(recipe.getMealType()));
        return recipeResponse;
    }

    @Transactional
    public RecipeResponse updateRecipe(String id, RecipeRequest request) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND));
        recipeIngredientRepository.deleteRecipe_IngredientByRecipe(recipe);
        return setRecipeToSaveAndResponse(request, id);
    }


    public String deleteRecipe(String id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND));
        recipeRepository.deleteById(id);
        return "Delete Recipe Successfully";
    }

    public Set<RecipeResponse> searchByIngredient(SearchRecipeByIngredientsRequest request) {
        if (request.getIngredients() == null || request.getIngredients().isEmpty()) {
            throw new AppException(ErrorCode.INGREDIENT_LIST_NOT_NULL);
        }
        Set<Recipe_Ingredient> recipe_ingrediens_list = new HashSet<>();
        for (String ingredient : request.getIngredients()) {
            List<Recipe_Ingredient> recipe_ingredient_obj = null;
            if (ingredientRepository.existsByIngredientName(ingredient)) {
                recipe_ingredient_obj = recipeIngredientRepository.findRecipe_IngredientByIngredient(
                        ingredientRepository.findByIngredientName(ingredient)
                );
            } else {
                List<Ingredient> ingredients = ingredientRepository.findByIngredientNameContaining(ingredient);
                if (ingredients.isEmpty()) {
                    throw new AppException(ErrorCode.INGREDIENT_NOT_FOUND);
                }
                for (Ingredient ingredient1 : ingredients) {
                    recipe_ingredient_obj = recipeIngredientRepository.findRecipe_IngredientByIngredient(ingredient1);
                }
            }
            if (recipe_ingredient_obj == null) {
                throw new AppException(ErrorCode.INGREDIENT_NOT_FOUND);
            }
            recipe_ingrediens_list.addAll(recipe_ingredient_obj);
        }
        if (recipe_ingrediens_list.isEmpty()) {
            throw new AppException(ErrorCode.INGREDIENT_NOT_FOUND);
        }
        Set<Recipe> recipes = new HashSet<>();
        for (Recipe_Ingredient recipe_ingredient : recipe_ingrediens_list) {
            Recipe recipe = recipeRepository.findRecipeByRecipeIngredients(recipe_ingredient);
            if (recipe != null)
                recipes.add(recipeRepository.findRecipeByRecipeIngredients(recipe_ingredient));
        }
        if (recipes.isEmpty()) {
            throw new AppException(ErrorCode.RECIPE_NOT_FOUND);
        }
        return recipes.stream().map(recipe -> {
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).collect(Collectors.toSet());
    }

    public List<RecipeResponse> getRecipesByMealType(String mealType) {
        try {
            MealType.valueOf(mealType);
        } catch (Exception e) {
            throw new AppException(ErrorCode.MEAL_TYPE_IS_INVALID);
        }
        List<Recipe> recipelist = recipeRepository.findByMealType(MealType.valueOf(mealType));
        return recipelist.stream().map(recipe -> {
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();
    }

    public List<RecipeResponse> getRecipesByNutritionalQuality(String nutritionalQuality) {
        try {
            NutritionalQuality.valueOf(nutritionalQuality);
        } catch (Exception e) {
            throw new AppException(ErrorCode.NUTRITIONAL_QUALITY_IS_INVALID);
        }
        List<Recipe> recipelist = recipeRepository.findByNutritionalQuality(NutritionalQuality.valueOf(nutritionalQuality));
        return recipelist.stream().map(recipe -> {
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();
    }

    public List<RecipeResponse> getRecipesByDifficultyLevel(String difficultyLevel) {
        try {
            DifficultyLevel.valueOf(difficultyLevel);
        } catch (Exception e) {
            throw new AppException(ErrorCode.DIFFICULTY_LEVEL_IS_INVALID);
        }
        List<Recipe> recipelist = recipeRepository.findByDifficultyLevel(DifficultyLevel.valueOf(difficultyLevel));
        return recipelist.stream().map(recipe -> {
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();
    }

    public List<RecipeResponse> getRecipesByMacroNutrients(String macroNutrient, Double minMacro, Double maxMacro) {

        List<Recipe> recipelist = null;
        if (Objects.equals(macroNutrient, "Calories")) {
            recipelist = recipeRepository.findByCaloriesRange(minMacro, maxMacro);
        }
        if (Objects.equals(macroNutrient, "Protein"))
            recipelist = recipeRepository.findByProteinRange(minMacro, maxMacro);
        if (Objects.equals(macroNutrient, "Carbs"))
            recipelist = recipeRepository.findByCarbsRange(minMacro, maxMacro);
        if (Objects.equals(macroNutrient, "Fat"))
            recipelist = recipeRepository.findByFatRange(minMacro, maxMacro);
        if (recipelist == null) {
            throw new AppException(ErrorCode.RECIPE_NOT_FOUND);
        }
        return recipelist.stream().map(recipe -> {
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();
    }

    public List<RecipeResponse> searchRecipeByName(String recipeName) {
        List<Recipe> recipelist = recipeRepository.findByRecipeNameContaining(recipeName);
        return recipelist.stream().map(recipe -> {
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();
    }

    // Get Recipe Based On Nutritional Calculation for Each User
    public RecipeResponseBaseOnNutritionPlan getRecipeBasedOnNutritionalCalculation(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        NutritionalCalculationResponse nutritionCalculation = nutritionalCalculationService.calculateNutrition(user);
        DietType dietType = user.getDietType();
        List<Recipe> recipeList;
        try {
            recipeList = recipeRepository.findByNutritionalQuality(NutritionalQuality.valueOf(user.getNutritionPlan().toString()));
        } catch (Exception e) {
            throw new AppException(ErrorCode.NUTRITIONAL_QUALITY_OF_RECIPE_AND_NUTRITION_PLSN_IS_NOT_MATCH);
        }
        // Initialize RecipeResponseBaseOnNutritionPlan
        RecipeResponseBaseOnNutritionPlan recipeResponseBaseOnNutritionPlan =
                new RecipeResponseBaseOnNutritionPlan().builder()
                        .meals(new ArrayList<>())
                        .build();

        // Logic for create Meal Response to add to Recipe Response
        MealResponseForNutritionPlan mealResponseForNutritionPlan;
        for (MealType mealType : MealType.values()) {
            mealResponseForNutritionPlan = MealResponseForNutritionPlan.builder()
                    .mealType(mealType.toString())
                    .recipeList(new ArrayList<>())
                    .build();
            for (Recipe recipe : recipeList) {
                if (recipe.getMealType().equals(mealType)) {
                    mealResponseForNutritionPlan.getRecipeList().add(
                            new SimpleRecipeResponse().builder()
                                    .recipeID(recipe.getRecipe_ID())
                                    .recipeName(recipe.getRecipeName())
                                    .imageURL(recipe.getImageURL())
                                    .calories(recipe.getTotalCalories())
                                    .protein(recipe.getTotalProtein())
                                    .fat(recipe.getTotalFat())
                                    .carbs(recipe.getTotalCarbs())
                                    .build()
                    );
                }
            }
            // Add Meal Response to Recipe Response
            recipeResponseBaseOnNutritionPlan.getMeals().add(mealResponseForNutritionPlan);
        }
        return recipeResponseBaseOnNutritionPlan;
    }


    // ----------------------------------------------
    // Private Methods for Business Logic
    // ----------------------------------------------
    private Set<MealType> setMealTypeResponse(Set<MealType> mealTypes) {
        Set<MealType> mealTypeResponse = new HashSet<>();
        mealTypeResponse.addAll(mealTypes);
        return mealTypeResponse;
    }


    @Transactional
    protected RecipeResponse setRecipeToSaveAndResponse(RecipeRequest request, String id) {
        // Check Valid Request

        RequestValidChecker(request);

        // Logic for Recipe Create and Update
        Recipe recipe = new Recipe();
        if (id != null) {
            recipe = recipeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND));
            recipeIngredientRepository.deleteRecipe_IngredientByRecipe(recipe);
            recipeMapper.updateRecipe(recipe, request);
        } else {
            UUID uuid = UUID.randomUUID();
            id = uuid.toString();
            recipe.setRecipe_ID(id);
            recipe = recipeMapper.toRecipe(request);
        }

        // Logic for totalCalories, totalProtein, totalCarbs, totalFat and validation for ingredients
        int totalCalories = 0;
        int totalProtein = 0;
        int totalCarbs = 0;
        int totalFat = 0;
        for (IngredientForRecipeRequest ingredient : request.getIngredientList()) {
            if (ingredient.getIngredientId() == null) {
                throw new AppException(ErrorCode.INGREDIENT_IN_LIST_NOT_NULL);
            }
            if (ingredientRepository.findById(ingredient.getIngredientId()).isEmpty()) {
                throw new AppException(ErrorCode.INGREDIENT_IN_LIST_NOT_FOUND);
            }
            totalCalories += ingredientRepository.findById(ingredient.getIngredientId()).get().getCalories() * ingredient.getQuantity();
            totalProtein += ingredientRepository.findById(ingredient.getIngredientId()).get().getProtein() * ingredient.getQuantity();
            totalCarbs += ingredientRepository.findById(ingredient.getIngredientId()).get().getCarbs() * ingredient.getQuantity();
            totalFat += ingredientRepository.findById(ingredient.getIngredientId()).get().getFat() * ingredient.getQuantity();
        }
        recipe.setTotalCalories(totalCalories);
        recipe.setTotalProtein(totalProtein);
        recipe.setTotalCarbs(totalCarbs);
        recipe.setTotalFat(totalFat);
        // Logic for MealType Create
        Set<MealType> mealTypeSet = new HashSet<>();
        for (String meal : request.getMealType()) {
            mealTypeSet.add(MealType.valueOf(meal));
        }
        recipe.setMealType(mealTypeSet);
        recipe = recipeRepository.save(recipe);
        // Logic for Recipe_Ingredient Create
        RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
        for (IngredientForRecipeRequest ingredient : request.getIngredientList()) {
            recipeIngredientService.createRecipeIngredient(recipe, ingredient);
        }
        //Logic for Ingredient Response
        recipeResponse.setIngredientList(recipeIngredientService.getIngredientsForRecipeResponse(recipe));

        //Logic for MealType Response
        recipeResponse.setMealType(setMealTypeResponse(recipe.getMealType()));
        return recipeResponse;
    }


    // Check Method
    public void RequestValidChecker(RecipeRequest request) {
        if (request.getRecipeName() == null) {
            throw new AppException(ErrorCode.RECIPE_NAME_REQUIRED);
        }
        if (request.getCookingInstructions() == null) {
            throw new AppException(ErrorCode.COOKING_INSTRUCTIONS_REQUIRED);
        }
        if (request.getImageURL() == null) {
            throw new AppException(ErrorCode.IMAGE_URL_REQUIRED);
        }
        if (request.getPrepTime() == null) {
            throw new AppException(ErrorCode.PREP_TIME_REQUIRED);
        }
        if (request.getPrepTime() < 0 || request.getPrepTime() > 120) {
            throw new AppException(ErrorCode.PREP_TIME_INVALID);
        }
        if (request.getCookTime() == null) {
            throw new AppException(ErrorCode.COOK_TIME_REQUIRED);
        }
        if (request.getCookTime() < 0 || request.getCookTime() > 120) {
            throw new AppException(ErrorCode.PREP_TIME_INVALID);
        }
        if (request.getNutritionalQuality() == null) {
            throw new AppException(ErrorCode.NUTRITIONAL_QUALITY_REQUIRED);
        }
        if (request.getMealType() == null) {
            throw new AppException(ErrorCode.MEAL_TYPE_REQUIRED);
        }
        if (request.getIngredientList() == null) {
            throw new AppException(ErrorCode.INGREDIENT_LIST_REQUIRED);
        }
        if (request.getDifficultyLevel() == null) {
            throw new AppException(ErrorCode.DIFFICULTY_LEVEL_REQUIRED);
        }
        try {
            NutritionalQuality.valueOf(request.getNutritionalQuality());
        } catch (Exception e) {
            throw new AppException(ErrorCode.NUTRITIONAL_QUALITY_IS_INVALID);
        }
    }
}

