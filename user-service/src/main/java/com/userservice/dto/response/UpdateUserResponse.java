package com.userservice.dto.response;

public record UpdateUserResponse(
        String firstName,

        String lastName,

        String password,

        String phoneNumber,

        String email
) {
}
