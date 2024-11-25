package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.PagingAndSortingAPIResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.IngredientRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.IngredientResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.error.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ingredient.IngredientType;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.IngredientMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Ingredient;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.IngredientRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.service.commonService.GenericPagingAndSortingService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IngredientService {
    IngredientRepository ingredientRepository;
    IngredientMapper ingredientMapper;
    GenericPagingAndSortingService genericPagingAndSortingService;

    public PagingAndSortingAPIResponse<IngredientResponse> getPagingAllIngredients(int page, int size, String[] sort) {
        Page<Ingredient> ingredients = ingredientRepository.findAll(genericPagingAndSortingService.createPageable(page, size, sort));
        List<IngredientResponse> ingredientsResponse = ingredients.map(ingredientMapper::toIngredientResponse).toList();
        return PagingAndSortingAPIResponse.<IngredientResponse>builder()
                .message("Success")
                .data(ingredientsResponse)
                .totalRecords(ingredients.getTotalElements())
                .totalPages(ingredients.getTotalPages())
                .pageNo(ingredients.getNumber() + 1)
                .pageSize(ingredients.getSize())
                .build();
    }

    public List<IngredientResponse> getAllIngredients() {
        var ingredients = ingredientRepository.findAll();
        return ingredients.stream().map(ingredientMapper::toIngredientResponse).toList();
    }

    public IngredientResponse createIngredient(IngredientRequest request) {
        // Valid Checker
        IngredientValidChecker(request);
        if (ingredientRepository.existsByIngredientName(request.getIngredientName()))
            throw new AppException(ErrorCode.INGREDIENT_EXIST);

        Ingredient ingredient = ingredientMapper.toIngredient(request);
        return ingredientMapper.toIngredientResponse(ingredientRepository.save(ingredient));
    }

    public IngredientResponse getIngredient(String id) {
        return ingredientMapper.toIngredientResponse(ingredientRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INGREDIENT_NOT_FOUND)));
    }

    public IngredientResponse updateIngredient(String id, IngredientRequest request) {
        // Valid Checker
        IngredientValidChecker(request);


        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INGREDIENT_NOT_FOUND));

        ingredientMapper.updateIngredient(ingredient, request);
        return ingredientMapper.toIngredientResponse(ingredientRepository.save(ingredient));
    }

    @Transactional
    public String deleteIngredient(String id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INGREDIENT_NOT_FOUND));
        ingredientRepository.deleteById(id);
        return "Ingredient deleted";
    }


    // Valid Checker Method
    public void IngredientValidChecker(IngredientRequest request) {
        try {
            IngredientType.valueOf(request.getIngredientType());
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.INGREDIENT_TYPE_INVALID);
        }
        if (request.getIngredientName().length() < 2 || request.getIngredientName().length() > 50 || request.getIngredientName() == null)
            throw new AppException(ErrorCode.INGREDIENT_NAME_INVALID);
        if (request.getIngredientType() == null)
            throw new AppException(ErrorCode.INGREDIENT_TYPE_REQUIRED);
        if (request.getCalories() == 0)
            throw new AppException(ErrorCode.INGREDIENT_CALORIES_REQUIRED);
        if (request.getProtein() == 0)
            throw new AppException(ErrorCode.INGREDIENT_PROTEIN_REQUIRED);
        if (request.getFat() == 0)
            throw new AppException(ErrorCode.INGREDIENT_FAT_REQUIRED);
        if (request.getCarbs() == 0)
            throw new AppException(ErrorCode.INGREDIENT_CARBS_REQUIRED);
        if (request.getUnit() == null)
            throw new AppException(ErrorCode.INGREDIENT_UNIT_REQUIRED);
        if (request.getImageURL() == null)
            throw new AppException(ErrorCode.IMAGE_URL_REQUIRED);
    }

}
