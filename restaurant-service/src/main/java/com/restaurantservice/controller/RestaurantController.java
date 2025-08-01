package com.restaurantservice.controller;

import com.restaurantservice.controller.mapper.RestaurantMapper;
import com.restaurantservice.domain.Restaurant;
import com.restaurantservice.dto.request.CreateRestaurantRequest;
import com.restaurantservice.dto.request.UpdateRestaurantRequest;
import com.restaurantservice.dto.response.FetchRestaurantResponse;
import com.restaurantservice.dto.response.RestaurantContactInfoResponse;
import com.restaurantservice.dto.response.UpdateRestaurantResponse;
import com.restaurantservice.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
@Validated
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final RestaurantContactInfoResponse restaurantContactInfoResponse;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createRestaurant(@RequestBody @Valid CreateRestaurantRequest createRestaurantRequest) {
        log.info("------------------------POST REQUEST------------------------");
        log.info("Received request to create restaurant: {}", createRestaurantRequest);
        restaurantService.create(restaurantMapper.toRestaurant(createRestaurantRequest));
        log.info("Restaurant created successfully");
        log.info("------------------------POST REQUEST END------------------------");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<FetchRestaurantResponse> fetchRestaurant(@PathVariable String id) {
        log.info("------------------------GET REQUEST------------------------");
        log.info("Received request to fetch restaurant with id: {}", id);
        FetchRestaurantResponse restaurantResponseDto = restaurantMapper.toFetchRestaurantResponseDto(
                restaurantService.fetch(id));
        log.info("Fetched restaurant: {}", restaurantResponseDto);
        log.info("------------------------GET REQUEST END------------------------");
        return ResponseEntity.ok(restaurantResponseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateRestaurantResponse> updateRestaurant(@PathVariable String id,
                                                                     @RequestBody @Valid UpdateRestaurantRequest updateRestaurantRequest) {
        log.info("------------------------PUT REQUEST------------------------");
        log.info("Received request to update restaurant: {}", id);
        Restaurant updatedRestaurant = restaurantService.update(id,
                restaurantMapper.toRestaurant(updateRestaurantRequest));
        log.info("Updated restaurant: {}", updatedRestaurant);
        log.info("------------------------PUT REQUEST END------------------------");
        return ResponseEntity.ok(restaurantMapper.toUpdateRestaurantResponseDto(updatedRestaurant));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
        log.info("------------------------DELETE REQUEST------------------------");
        log.info("Received request to delete restaurant with id: {}", id);
        restaurantService.delete(id);
        log.info("Restaurant with id: {} deleted successfully", id);
        log.info("------------------------DELETE REQUEST END------------------------");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/fetch/contact-info")
    public ResponseEntity<RestaurantContactInfoResponse> fetchContactInfo() {
        log.info("------------------------GET REQUEST------------------------");
        log.info("Received request to fetch contact information");
        return ResponseEntity.status(HttpStatus.OK)
                .body(restaurantContactInfoResponse);
    }

    @GetMapping("/fetch")
    public ResponseEntity<FetchRestaurantResponse> fetchRestaurantByPhone(@RequestParam String phone) {
        log.info("------------------------GET REQUEST------------------------");
        log.info("Received request to fetch restaurant information");
        FetchRestaurantResponse restaurantResponse = restaurantMapper.toFetchRestaurantResponseDto(restaurantService.findByPhone(phone));
        log.info("Fetched restaurant: {}", restaurantResponse);
        log.info("------------------------GET REQUEST END------------------------");
        return ResponseEntity.ok(restaurantResponse);
    }
}
