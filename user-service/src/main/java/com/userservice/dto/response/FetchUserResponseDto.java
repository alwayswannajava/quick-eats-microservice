package com.userservice.dto.response;

import com.userservice.domain.User;

public record FetchUserResponseDto(
        String firstName,

        String lastName,

        String phoneNumber,

        String email,

        User.Role role
) {
}
