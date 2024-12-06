package cap2.example.Capstone2_BackEnd.NutriApp.controller;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.favorite.request.FavoriteRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.favorite.response.FavoriteResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.favorite.response.TrendingRecipeResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.service.FavoriteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteController {

    FavoriteService favoriteService;


    @GetMapping("/all")
    public ApiResponse<List<FavoriteResponse>> getAllFavorite() {
        ApiResponse<List<FavoriteResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(favoriteService.getAllFavorite());
        apiResponse.setMessage("Get all favorite recipe successfully");
        return apiResponse;
    }

    @GetMapping("/{favoriteId}")
    public ApiResponse<FavoriteResponse> getFavoriteById(@PathVariable String favoriteId) {
        ApiResponse<FavoriteResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(favoriteService.getFavoriteById(favoriteId));
        apiResponse.setMessage("Get favorite recipe successfully");
        return apiResponse;
    }

    @PostMapping("")
    public ApiResponse<FavoriteResponse> createFavorite(@RequestBody FavoriteRequest request) {
        ApiResponse<FavoriteResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(favoriteService.createFavorite(request));
        apiResponse.setMessage("Create favorite recipe successfully");
        return apiResponse;
    }

    @PutMapping("/{favoriteId}")
    public ApiResponse<FavoriteResponse> updateFavorite(@PathVariable String favoriteId, @RequestBody FavoriteRequest request) {
        ApiResponse<FavoriteResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(favoriteService.updateFavorite(favoriteId, request));
        apiResponse.setMessage("Update favorite recipe successfully");
        return apiResponse;
    }

    @DeleteMapping("/{favoriteId}")
    public ApiResponse<String> deleteFavorite(@PathVariable String favoriteId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(favoriteService.deleteFavorite(favoriteId));
        apiResponse.setMessage("Delete favorite recipe successfully");
        return apiResponse;
    }


    @GetMapping("/user/{userId}")
    public ApiResponse<List<FavoriteResponse>> getFavoriteRecipeOfUser(@PathVariable String userId) {
        ApiResponse<List<FavoriteResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(favoriteService.getFavoriteRecipeOfUser(userId));
        apiResponse.setMessage("Get favorite recipe of user successfully");
        return apiResponse;
    }

    @GetMapping("/trending")
    public ApiResponse<List<TrendingRecipeResponse>> getTrendingFavoriteRecipe() {
        ApiResponse<List<TrendingRecipeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(favoriteService.getTrendingRecipe());
        apiResponse.setMessage("Get trending favorite recipe successfully");
        return apiResponse;
    }
}


