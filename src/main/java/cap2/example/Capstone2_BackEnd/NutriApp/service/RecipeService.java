package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.PagingAndSortingAPIResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.RecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.RecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe.SearchRecipeByIngredientsRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.IngredientForRecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.recipe_ingredient.IngredientForRecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.MealType;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.RecipeMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe_Ingredient;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.IngredientRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeIngredientRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.service.commonService.GenericPagingAndSortingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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
            recipeResponse.setMealTypeList(setMealTypeResponse(recipe.getMealType()));
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
        recipeResponse.setMealTypeList(setMealTypeResponse(recipe.getMealType()));
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
            List<Recipe_Ingredient> recipe_ingredient_obj = recipeIngredientRepository.findRecipe_IngredientByIngredient(
                    ingredientRepository.findByIngredientName(ingredient)
            );
            if (recipe_ingredient_obj == null) {
                throw new AppException(ErrorCode.INGREDIENT_NOT_FOUND);
            }
            for (Recipe_Ingredient recipe_ingredient : recipe_ingredient_obj)
                recipe_ingrediens_list.add(recipe_ingredient);
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

    public List<RecipeResponse> getRecipesByCaloriesRange(Double minCalories, Double maxCalories) {
        List<Recipe> recipelist = recipeRepository.findByCaloriesRange(minCalories, maxCalories);
        return recipelist.stream().map(recipe -> {
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();
    }

    public List<RecipeResponse> getRecipesByProteinRange(Double minProtein, Double maxProtein) {
        List<Recipe> recipelist = recipeRepository.findByProteinRange(minProtein, maxProtein);
        return recipelist.stream().map(recipe -> {
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();
    }

    public List<RecipeResponse> getRecipesByCarbsRange(Double minCarbs, Double maxCarbs) {
        List<Recipe> recipelist = recipeRepository.findByCarbsRange(minCarbs, maxCarbs);
        return recipelist.stream().map(recipe -> {
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();
    }

    public List<RecipeResponse> getRecipesByFatRange(Double minFat, Double maxFat) {
        List<Recipe> recipelist = recipeRepository.findByFatRange(minFat, maxFat);
        return recipelist.stream().map(recipe -> {
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();
    }


    // Private Methods for Business Logic
    private Set<MealType> setMealTypeResponse(Set<MealType> mealTypes) {
        Set<MealType> mealTypeResponse = new HashSet<>();
        mealTypeResponse.addAll(mealTypes);
        return mealTypeResponse;
    }


    @Transactional
    protected RecipeResponse setRecipeToSaveAndResponse(RecipeRequest request, String id) {
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
        Double totalCalories = 0.0;
        Double totalProtein = 0.0;
        Double totalCarbs = 0.0;
        Double totalFat = 0.0;
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
        recipeResponse.setMealTypeList(setMealTypeResponse(recipe.getMealType()));
        return recipeResponse;
    }
}

