package com.userservice.controller.mapper;

import com.userservice.config.MapperConfig;
import com.userservice.domain.User;
import com.userservice.dto.request.CreateUserRequestDto;
import com.userservice.dto.request.UpdateUserRequestDto;
import com.userservice.dto.response.FetchUserResponseDto;
import com.userservice.dto.response.UpdateUserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserRequestDto createUserRequestDto);

    User toUser(UpdateUserRequestDto updateUserRequestDto);

    FetchUserResponseDto toFetchUserResponseDto(User user);

    UpdateUserResponseDto toUpdateUserResponseDto(User user);
}
