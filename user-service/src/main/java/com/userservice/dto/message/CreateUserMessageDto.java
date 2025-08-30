package com.userservice.dto.message;

public record CreateUserMessageDto(
        String firstName,
        String lastName,
        String phone,
        String email
) {
}
