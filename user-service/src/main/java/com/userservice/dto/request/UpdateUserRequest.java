package com.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @NotBlank
        @Size(min = 2, max = 25, message = "First name must be between 2 and 25 characters")
        String firstName,

        @NotBlank
        @Size(min = 2, max = 25, message = "Last name must be between 2 and 25 characters")
        String lastName,

        @NotBlank
        @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
        String password,

        @NotBlank
        @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number format")
        String phoneNumber,

        @NotBlank
        @Email
        String email
){
}
