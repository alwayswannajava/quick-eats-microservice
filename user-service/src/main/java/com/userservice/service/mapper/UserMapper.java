package com.userservice.service.mapper;

import com.userservice.domain.User;
import com.userservice.dto.request.CreateUserRequestDto;
import com.userservice.dto.request.UpdateUserRequestDto;
import com.userservice.dto.response.FetchUserResponseDto;
import com.userservice.dto.response.UpdateUserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserRequestDto createUserRequestDto);

    User toUser(UpdateUserRequestDto updateUserRequestDto);

    FetchUserResponseDto toFetchUserResponseDto(User user);

    UpdateUserResponseDto toUpdateUserResponseDto(User user);
}
