package com.restaurantservice.service.impl;

import com.mongodb.MongoException;
import com.restaurantservice.common.RestaurantStatus;
import com.restaurantservice.domain.Restaurant;
import com.restaurantservice.domain.WorkingHours;
import com.restaurantservice.dto.request.RestaurantFilter;
import com.restaurantservice.repository.RestaurantRepository;
import com.restaurantservice.service.RestaurantService;
import com.restaurantservice.service.exception.RestaurantNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void create(Restaurant restaurant) {
        log.info("Creating restaurant: {}", restaurant);
        try {
            restaurantRepository.save(restaurant);
        } catch (Exception e) {
            log.error("Error while creating restaurant: {}", restaurant, e);
            throw new MongoException("Error while creating restaurant: " + e.getMessage());
        }
        log.info("Restaurant created successfully: {}", restaurant);
    }

    @Override
    public List<Restaurant> fetchAll(Pageable pageable) {
        return List.of();
    }

    @Override
    public Restaurant fetch(String id) {
    log.info("Fetching restaurant with id: {}", id);
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException
                        ("Restaurant not found with id: " + id));
    }

    @Override
    public Restaurant update(String id, Restaurant restaurant) {
        log.info("Updating restaurant with id: {}", id);
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException
                        ("Restaurant not found with id: " + id));

        try {
            return restaurantRepository.save(existingRestaurant);
        } catch (Exception e) {
            log.error("Error while updating restaurant with id: {}", id, e);
            throw new MongoException("Error while updating restaurant: " + e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public List<Restaurant> findNearbyRestaurants(BigDecimal latitude, BigDecimal longitude, BigDecimal radiusKm) {
        return List.of();
    }

    @Override
    public List<Restaurant> searchRestaurants(String query, RestaurantFilter filter) {
        return List.of();
    }

    @Override
    public List<Restaurant> getRestaurantsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Restaurant> getOpenRestaurants() {
        return List.of();
    }

    @Override
    public boolean isRestaurantOpen(String restaurantId) {
        return false;
    }

    @Override
    public boolean isRestaurantOpenAt(String restaurantId, LocalDateTime dateTime) {
        return false;
    }

    @Override
    public Restaurant updateWorkingHours(String id, WorkingHours workingHours) {
        return null;
    }

    @Override
    public Restaurant activateRestaurant(String id) {
        return null;
    }

    @Override
    public Restaurant deactivateRestaurant(String id) {
        return null;
    }

    @Override
    public Restaurant updateRestaurantStatus(String id, RestaurantStatus status) {
        return null;
    }
}
