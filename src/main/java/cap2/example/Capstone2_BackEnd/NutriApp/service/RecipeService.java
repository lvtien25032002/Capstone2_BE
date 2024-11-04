package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.PagingAndSortingAPIResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe.RecipeCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe.RecipeUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe.SearchRecipeByIngredientsRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe_ingredient.IngredientForRecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe.RecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe_ingredient.IngredientForRecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
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
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();

    }


    public RecipeResponse createRecipe(RecipeCreateRequest request) {
        if (recipeRepository.existsByRecipeName(request.getRecipeName())) {
            throw new AppException(ErrorCode.RECIPE_EXIST);
        }
        UUID uuid = UUID.randomUUID();
        Recipe recipe = recipeMapper.toRecipe(request);
        recipe.setRecipe_ID(uuid.toString());
        Set<IngredientForRecipeRequest> ingredients = Set.copyOf(request.getIngredientList());
        for (IngredientForRecipeRequest ingredient : ingredients) {
            if (ingredient.getIngredientId() == null) {
                throw new AppException(ErrorCode.INGREDIENT_IN_LIST_NOT_NULL);
            }
            if (ingredientRepository.findById(ingredient.getIngredientId()).isEmpty()) {
                throw new AppException(ErrorCode.INGREDIENT_IN_LIST_NOT_FOUND);
            }
        }
        recipe = recipeRepository.save(recipe);
        RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
        for (IngredientForRecipeRequest ingredient : ingredients) {
            recipeIngredientService.createRecipeIngredient(recipe, ingredient);
        }
        List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
        recipeResponse.setIngredientList(ingredientList);
        return recipeResponse;
    }


    public RecipeResponse getRecipe(String id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND));
        List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
        RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
        recipeResponse.setIngredientList(ingredientList);
        return recipeResponse;
    }


    public RecipeResponse updateRecipe(String id, RecipeUpdateRequest request) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND));
        recipeMapper.updateRecipe(recipe, request);

        return recipeMapper.toRecipeResponse(recipeRepository.save(recipe));
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
}

