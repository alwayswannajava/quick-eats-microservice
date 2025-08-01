package com.userservice.service.client;

import com.userservice.dto.response.client.FetchRestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("restaurant-service")
public interface RestaurantFeignClient {
    @GetMapping(value = "/api/fetch",consumes = "application/json")
    ResponseEntity<FetchRestaurantResponse> fetchRestaurantDetails(@RequestParam String mobileNumber);
}
