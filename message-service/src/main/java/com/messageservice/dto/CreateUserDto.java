package com.messageservice.dto;

public record CreateUserDto(
        String firstName,
        String lastName,
        String phone,
        String email
) {
}
