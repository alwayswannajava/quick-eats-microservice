package com.userservice.dto.request;

import com.userservice.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record FetchUserRequestDto(
        UUID userId
) {
}
