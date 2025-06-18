package com.restaurantservice.service;

import com.restaurantservice.common.RestaurantStatus;
import com.restaurantservice.domain.Restaurant;
import com.restaurantservice.domain.WorkingHours;
import com.restaurantservice.dto.request.RestaurantFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface RestaurantService {
    void create(Restaurant restaurant);

    Page<Restaurant> fetchAll(Pageable pageable);

    Restaurant fetch(String id);

    Restaurant update(String id, Restaurant restaurant);

    void delete(String id);

    List<Restaurant> findByLocation(Point point, Distance distance);

    List<Restaurant> findNearbyRestaurants(BigDecimal latitude, BigDecimal longitude, BigDecimal radiusKm);

    List<Restaurant> searchRestaurants(String query, RestaurantFilter filter);

    List<Restaurant> fetchOpenRestaurants();

    boolean isRestaurantOpen(String restaurantId);

    boolean isRestaurantOpenAt(String restaurantId, LocalTime time);

    Restaurant updateWorkingHours(String id, WorkingHours workingHours);

    Restaurant activateRestaurant(String id);

    Restaurant deactivateRestaurant(String id);

    Restaurant updateRestaurantStatus(String id, RestaurantStatus status);
}
