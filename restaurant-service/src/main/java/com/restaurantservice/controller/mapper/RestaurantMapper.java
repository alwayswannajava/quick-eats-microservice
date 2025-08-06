package com.restaurantservice.controller.mapper;

import com.restaurantservice.config.MapperConfig;
import com.restaurantservice.domain.Restaurant;
import com.restaurantservice.dto.request.CreateRestaurantRequest;
import com.restaurantservice.dto.request.UpdateRestaurantRequest;
import com.restaurantservice.dto.response.FetchRestaurantResponse;
import com.restaurantservice.dto.response.UpdateRestaurantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface RestaurantMapper {
    @Mapping(target = "id", ignore = true)
    void toRestaurant(@MappingTarget Restaurant existingRestaurant, Restaurant restaurant);

    Restaurant toRestaurant(CreateRestaurantRequest createRestaurantRequest);

    Restaurant toRestaurant(UpdateRestaurantRequest updateRestaurantRequest);

    FetchRestaurantResponse toFetchRestaurantResponseDto(Restaurant restaurant);

    UpdateRestaurantResponse toUpdateRestaurantResponseDto(Restaurant restaurant);
}
