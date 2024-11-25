package cap2.example.Capstone2_BackEnd.NutriApp.service;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.favorite.request.FavoriteRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.favorite.response.FavoriteResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.favorite.response.TrendingRecipe;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.error.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import cap2.example.Capstone2_BackEnd.NutriApp.mapper.FavoriteMapper;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Favorite;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.FavoriteRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.RecipeRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository.UserRepository;
import cap2.example.Capstone2_BackEnd.NutriApp.service.commonService.GenericPagingAndSortingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteService {
    // Repository
    FavoriteRepository favoriteRepository;
    UserRepository userRepository;
    RecipeRepository recipeRepository;

    // Mappper
    FavoriteMapper favoriteMapper;

    //Pageable
    Pageable pageable = PageRequest.of(0, 10);
    GenericPagingAndSortingService genericPagingAndSortingService;
    ;

    // Method
    public List<FavoriteResponse> getAllFavorite() {
        List<Favorite> favorites = favoriteRepository.findAll();
        return favorites.stream().map(
                favorite -> returnFavoriteResponse(favorite)
        ).toList();
    }

    public FavoriteResponse getFavoriteById(String favoriteId) {
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new AppException(ErrorCode.FAVORITE_NOT_FOUND));
        return returnFavoriteResponse(favorite);
    }

    public FavoriteResponse createFavorite(FavoriteRequest request) {
        Favorite favorite = Favorite.builder()
                .User_ID(userRepository.findById(request.getUserID())
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)))
                .Recipe_ID(recipeRepository.findById(request.getRecipeID())
                        .orElseThrow(() -> new AppException(ErrorCode.RECIPE_NOT_FOUND)))
                .dateAdded(LocalDate.now())
                .build();
        favoriteRepository.save(favorite);
        return returnFavoriteResponse(favorite);
    }

    public FavoriteResponse updateFavorite(String favoriteId, FavoriteRequest request) {
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new AppException(ErrorCode.FAVORITE_NOT_FOUND));
        favoriteMapper.updateFavorite(favorite, request);
        favoriteRepository.save(favorite);
        return returnFavoriteResponse(favorite);
    }

    public String deleteFavorite(String favoriteId) {
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new AppException(ErrorCode.FAVORITE_NOT_FOUND));
        favoriteRepository.deleteById(favoriteId);
        return "Delete favorite recipe successfully";
    }

    public List<FavoriteResponse> getFavoriteRecipeOfUser(String userId) {
        List<Favorite> favorites = favoriteRepository.findByUser_ID(userId);
        return favorites.stream().map(
                favorite -> returnFavoriteResponse(favorite)
        ).toList();
    }

    public List<TrendingRecipe> getTrendingRecipe() {
        Page<Favorite> trendingRecipes = favoriteRepository.findAll(genericPagingAndSortingService.createPageable(0, 10, null));

        List<TrendingRecipe> response = new ArrayList<>();
        trendingRecipes.map(favorite -> {
            TrendingRecipe trendingRecipe = TrendingRecipe.builder()
                    .favoriteID(favorite.getFavorite_ID())
                    .recipeID(favorite.getRecipe_ID().getRecipe_ID())
                    .build();
            response.add(trendingRecipe);
            return favorite;
        });
        return response;
    }

    // Private Method

    protected FavoriteResponse returnFavoriteResponse(Favorite favorite) {
        FavoriteResponse favoriteResponse = FavoriteResponse.builder()
                .favoriteID(favorite.getFavorite_ID())
                .userID(favorite.getUser_ID().getUser_ID())
                .recipeID(favorite.getRecipe_ID().getRecipe_ID())
                .dateAdded(favorite.getDateAdded())
                .build();

        return favoriteResponse;
    }
}
