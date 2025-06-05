package com.userservice.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserResponseDto(
        String firstName,

        String lastName,

        String password,

        String phoneNumber,

        String email
) {
}
