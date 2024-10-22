package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe.RecipeCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe.RecipeUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.recipe_ingredient.IngredientForRecipeRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe.RecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.recipe_ingredient.IngredientForRecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.RecipeMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Image;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Recipe;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.IngredientRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeIngredientRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.commonRepository.ImageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeService {
    RecipeRepository recipeRepository;
    IngredientRepository ingredientRepository;
    RecipeMapper recipeMapper;
    ImageRepository imageRepository;
    RecipeIngredientService recipeIngredientService;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecipeResponse createRecipe(RecipeCreateRequest request) {
        if (recipeRepository.existsByRecipeName(request.getRecipeName())) {
            throw new AppException(ErrorCode.RECIPE_EXIST);
        }
        UUID uuid = UUID.randomUUID();
        Recipe recipe = recipeMapper.toRecipe(request);
        recipe.setRecipe_ID(uuid.toString());
        Image image = imageRepository.findImageByUrl(request.getImageURL());
        if (image == null)
            throw new AppException(ErrorCode.IMAGE_NOT_FOUND);

        recipe.setImageURL(image);
        Set<IngredientForRecipeRequest> ingredients = Set.copyOf(request.getIngredientList());
        for (IngredientForRecipeRequest ingredient : ingredients) {
            if (!ingredientRepository.existsByIngredientName(ingredient.getIngredientName())) {
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

    public List<RecipeResponse> getAllRecipes() {
        List<RecipeResponse> recipeListResponse = recipeRepository.findAll().stream().map(recipe -> {
            RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
            List<IngredientForRecipeResponse> ingredientList = recipeIngredientService.getIngredientsForRecipeResponse(recipe);
            recipeResponse.setIngredientList(ingredientList);
            return recipeResponse;
        }).toList();
        return recipeListResponse;

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
}

