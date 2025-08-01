package com.userservice.dto.response.client;

import com.userservice.domain.User;

public record UserClientResponse(
        String firstName,

        String lastName,

        String phone,

        String email,

        User.Role role,

        FetchRestaurantResponse restaurants,

        FetchOrderResponse orders
) {
}
