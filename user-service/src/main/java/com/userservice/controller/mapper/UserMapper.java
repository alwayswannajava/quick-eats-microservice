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
    @Mapping(source = "password", target = "passwordHash")
    User toUser(CreateUserRequest createUserRequest);

    User toUser(@MappingTarget User existingUser, User user);

    @Mapping(source = "password", target = "passwordHash")
    User toUser(UpdateUserRequest updateUserRequest);

    FetchUserResponse toFetchUserResponse(User user);

    @Mapping(source = "passwordHash", target = "password")
    User toUser(CreateUserRequest createUserRequest);

    UpdateUserResponse toUpdateUserResponse(User user);
}
