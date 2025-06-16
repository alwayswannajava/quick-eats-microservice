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
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        log.info("Deleting restaurant with id: {}", id);
        restaurantRepository.deleteById(id);
        log.info("Restaurant with id: {} deleted successfully", id);
    }

    @Override
    public List<Restaurant> findByLocation(Point point, Distance distance) {
        log.info("Finding restaurants near point: {}, within distance: {}", point, distance);
        return restaurantRepository.findByLocationNear(point, distance);
    }

    @Override
    public List<Restaurant> findNearbyRestaurants(BigDecimal latitude, BigDecimal longitude, BigDecimal radiusKm) {
        log.info("Finding nearbyRestaurants for latitude: {}, longitude: {}, radiusKm: {}", latitude, longitude, radiusKm);
        Point point = new Point(latitude.doubleValue(), longitude.doubleValue());
        Distance distance = new Distance(radiusKm.doubleValue(), Metrics.KILOMETERS);
        return restaurantRepository.findByLocationNear(point, distance);

    }

    @Override
    public List<Restaurant> searchRestaurants(String query, RestaurantFilter filter) {
        return List.of();
    }

    @Override
    public List<Restaurant> fetchOpenRestaurants() {
        log.info("Fetching open restaurants");
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getStatus() == RestaurantStatus.ACTIVE)
                .toList();
    }

    @Override
    public boolean isRestaurantOpen(String restaurantId) {
        log.info("Checking if restaurant with id: {} is open", restaurantId);
        return restaurantRepository.findById(restaurantId)
                .map(restaurant -> restaurant.getStatus() == RestaurantStatus.ACTIVE)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + restaurantId));
    }

    @Override
    public boolean isRestaurantOpenAt(String restaurantId, LocalTime time) {
        log.info("Checking if restaurant with id: {} is open at time: {}", restaurantId, time);
        return restaurantRepository.findById(restaurantId)
                .map(restaurant -> {
                    WorkingHours workingHours = restaurant.getWorkingHours();
                    if (workingHours == null) {
                        return false;
                    }
                    return workingHours.getFriday().isOpenAt(time);
                })
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + restaurantId));
    }

    @Override
    public Restaurant updateWorkingHours(String id, WorkingHours workingHours) {
        log.info("Updating working hours for restaurant with id: {}", id);
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setWorkingHours(workingHours);
                    return restaurantRepository.save(restaurant);
                })
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id));
    }

    @Override
    public Restaurant activateRestaurant(String id) {
        log.info("Activating restaurant with id: {}", id);
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setStatus(RestaurantStatus.ACTIVE);
                    return restaurantRepository.save(restaurant);
                })
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id));
    }

    @Override
    public Restaurant deactivateRestaurant(String id) {
        log.info("Deactivating restaurant with id: {}", id);
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setStatus(RestaurantStatus.INACTIVE);
                    return restaurantRepository.save(restaurant);
                })
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id));
    }

    @Override
    public Restaurant updateRestaurantStatus(String id, RestaurantStatus status) {
        log.info("Updating status for restaurant with id: {} to {}", id, status);
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setStatus(status);
                    return restaurantRepository.save(restaurant);
                })
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id));
    }
}
