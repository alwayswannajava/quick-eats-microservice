package com.userservice.controller.mapper;

import com.userservice.config.MapperConfig;
import com.userservice.domain.User;
import com.userservice.dto.request.CreateUserRequest;
import com.userservice.dto.request.UpdateUserRequest;
import com.userservice.dto.response.FetchUserResponse;
import com.userservice.dto.response.UpdateUserResponse;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserRequest createUserRequest);

    User toUser(UpdateUserRequest updateUserRequest);

    FetchUserResponse toFetchUserResponseDto(User user);

    UpdateUserResponse toUpdateUserResponseDto(User user);
}
