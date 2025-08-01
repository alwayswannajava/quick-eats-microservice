package com.userservice.service.client;

import com.userservice.dto.response.client.FetchOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("order-service")
public interface OrderFeignClient {

    @GetMapping(value = "/api/fetch",consumes = "application/json")
    ResponseEntity<FetchOrderResponse> fetchOrderDetails(@RequestParam String mobileNumber);
}
