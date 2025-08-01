package com.userservice.service.impl;

import com.userservice.controller.mapper.UserMapper;
import com.userservice.domain.User;
import com.userservice.dto.response.client.FetchOrderResponse;
import com.userservice.dto.response.client.FetchRestaurantResponse;
import com.userservice.dto.response.client.UserClientResponse;
import com.userservice.service.UserClientService;
import com.userservice.service.UserService;
import com.userservice.service.client.OrderFeignClient;
import com.userservice.service.client.RestaurantFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserClientServiceImpl implements UserClientService {
    private final RestaurantFeignClient restaurantFeignClient;
    private final OrderFeignClient orderFeignClient;
    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    public UserClientResponse fetchUserClientDetails(String mobileNumber) {
        log.info("fetchUserClientDetails mobileNumber: {}", mobileNumber);
        User user = userService.fetchUserByPhone(mobileNumber);

        log.info("Entering at feign clients...");

        ResponseEntity<FetchRestaurantResponse> restaurantsDetails = restaurantFeignClient.fetchRestaurantDetails(mobileNumber);
        ResponseEntity<FetchOrderResponse> ordersDetails = orderFeignClient.fetchCardDetails(mobileNumber);

        log.info("Exiting from feign clients...Fetching is done");
        return userMapper.toUserClientResponse(user, ordersDetails.getBody(), restaurantsDetails.getBody());
    }
}
