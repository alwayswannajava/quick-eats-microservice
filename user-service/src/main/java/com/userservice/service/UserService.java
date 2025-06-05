package com.userservice.service;

import com.userservice.domain.User;
import com.userservice.dto.request.CreateUserRequestDto;
import com.userservice.dto.request.FetchUserRequestDto;
import com.userservice.dto.request.UpdateUserRequestDto;
import com.userservice.dto.response.UpdateUserResponseDto;
import java.util.UUID;

public interface UserService {
    void create(User user);

    User fetchUser(UUID userId);

    User update(UUID userId, User user);

    void delete(UUID userId);
}
