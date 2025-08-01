package com.userservice.controller.mapper;

import com.userservice.config.MapperConfig;
import com.userservice.domain.User;
import com.userservice.dto.request.CreateUserRequest;
import com.userservice.dto.request.UpdateUserRequest;
import com.userservice.dto.response.FetchUserResponse;
import com.userservice.dto.response.UpdateUserResponse;
import com.userservice.dto.response.client.FetchOrderResponse;
import com.userservice.dto.response.client.FetchRestaurantResponse;
import com.userservice.dto.response.client.UserClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "password", target = "passwordHash")
    User toUser(CreateUserRequest createUserRequest);

    User toUser(@MappingTarget User existingUser, User user);

    @Mapping(source = "password", target = "passwordHash")
    User toUser(UpdateUserRequest updateUserRequest);

    FetchUserResponse toFetchUserResponseDto(User user);

    @Mapping(source = "passwordHash", target = "password")
    UpdateUserResponse toUpdateUserResponseDto(User user);

    UserClientResponse toUserClientResponse(User user,
                                               FetchOrderResponse orderResponse,
                                               FetchRestaurantResponse restaurantResponse);
}
