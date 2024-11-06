package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.PagingAndSortingAPIResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.ingredient.IngredientCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.ingredient.IngredientUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.ingredient.ingredient.IngredientResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
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

    public IngredientResponse createIngredient(IngredientCreateRequest request) {
        if (ingredientRepository.existsByIngredientName(request.getIngredientName()))
            throw new AppException(ErrorCode.INGREDIENT_EXIST);
        Ingredient ingredient = ingredientMapper.toIngredient(request);
        return ingredientMapper.toIngredientResponse(ingredientRepository.save(ingredient));
    }

    public IngredientResponse getIngredient(String id) {
        return ingredientMapper.toIngredientResponse(ingredientRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INGREDIENT_NOT_FOUND)));
    }

    public IngredientResponse updateIngredient(String id, IngredientUpdateRequest request) {
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


}
