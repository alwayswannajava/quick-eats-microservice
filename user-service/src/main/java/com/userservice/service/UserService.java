package com.userservice.service;

import com.userservice.dto.request.CreateUserRequestDto;
import com.userservice.dto.request.GetUserRequestDto;
import com.userservice.dto.response.UpdateUserResponseDto;

public interface UserService {
    void create(CreateUserRequestDto createUserRequestDto);

    GetUserRequestDto fetchUser(String userId);

    UpdateUserResponseDto update(String userId);

    void delete(String userId);
}
