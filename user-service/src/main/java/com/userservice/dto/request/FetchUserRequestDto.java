package com.userservice.dto.request;

import java.util.UUID;

public record FetchUserRequestDto(
        UUID userId
) {
}
