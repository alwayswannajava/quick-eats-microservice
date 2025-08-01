package com.restaurantservice.repository;

import com.restaurantservice.domain.Restaurant;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findByLocationNear(Point point, Distance distance);

    Optional<Restaurant> findByPhone(String phone);
}
