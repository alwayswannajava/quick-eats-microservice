package com.userservice.service.mapper;

import com.userservice.config.MapperConfig;
import com.userservice.domain.User;
import com.userservice.dto.request.CreateUserRequestDto;
import com.userservice.dto.response.CreateUserResponseDto;
import com.userservice.dto.response.FetchUserResponseDto;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toUser(CreateUserRequestDto createUserRequestDto);

    CreateUserResponseDto toCreateUserResponseDto(User user);

    FetchUserResponseDto toFetchUserResponseDto(User user);
}
