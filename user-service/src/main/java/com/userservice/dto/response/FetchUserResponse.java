package com.userservice.dto.response;

import com.userservice.domain.User;

public record FetchUserResponse(
        String firstName,

        String lastName,

        String phone,

        String email,

        User.Role role
) {
}
