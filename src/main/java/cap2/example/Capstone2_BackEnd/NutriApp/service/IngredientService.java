package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.ingredient.IngredientCreateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.request.ingredient.IngredientUpdateRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.response.ingredient.IngredientResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.IngredientMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Ingredient;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.IngredientRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IngredientService {
    IngredientRepository ingredientRepository;
    IngredientMapper ingredientMapper;

    public Ingredient createIngredient(IngredientCreateRequest request) {
        if(ingredientRepository.existsByIngredientName(request.getIngredientName()))
            throw new AppException(ErrorCode.INGREDIENT_EXIST);
        Ingredient ingredient = ingredientMapper.toIngredient(request);
        return ingredientRepository.save(ingredient);
    }

    public List<IngredientResponse> getAllIngredients() {
        var ingredients = ingredientRepository.findAll();
        return ingredients.stream().map(ingredientMapper::toIngredientResponse).toList();
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

    public String deleteIngredient(String id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INGREDIENT_NOT_FOUND));
        ingredientRepository.deleteById(id);
        return "Ingredient deleted";
    }
}
