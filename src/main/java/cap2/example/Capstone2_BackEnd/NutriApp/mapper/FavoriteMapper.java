package cap2.example.Capstone2_BackEnd.NutriApp.mapper;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.favorite.request.FavoriteRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    Favorite updateFavorite(Favorite favorite, FavoriteRequest request);

    @Mapping(ignore = true, target = "dateAdded")
    Favorite toFavorite(FavoriteRequest request);
    
}
