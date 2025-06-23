package com.userservice.dto.request;

import com.userservice.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "First name is mandatory")
        @Size(min = 2, max = 25, message = "First name must be between 2 and 25 characters")
        String firstName,

        @NotBlank(message = "Last name is mandatory")
        @Size(min = 2, max = 25, message = "Last name must be between 2 and 25 characters")
        String lastName,

        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
        String password,

        @NotBlank(message = "Phone number is mandatory")
        @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number format")
        String phone,

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Role is mandatory")
        User.Role role
) {
}
