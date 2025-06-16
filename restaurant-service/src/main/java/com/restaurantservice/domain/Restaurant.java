package com.restaurantservice.domain;

import com.restaurantservice.common.CuisineType;
import com.restaurantservice.common.RestaurantStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "restaurants")
public class Restaurant {

    @Id
    private String id;

    @Field("owner_id")
    private String ownerId;

    private String name;

    private String description;

    @Field("cuisine_type")
    private List<CuisineType> cuisineType;

    private String logo;

    @Field("cover_image")
    private String coverImage;

    private String phone;

    private String email;

    private GeoJsonPoint location;

    @Field("working_hours")
    private WorkingHours workingHours;

    @Field("delivery_fee")
    private BigDecimal deliveryFee;

    @Field("min_order_amount")
    private BigDecimal minOrderAmount;

    @Field("delivery_radius")
    private BigDecimal deliveryRadius;

    @Field("average_delivery_time")
    private Integer averageDeliveryTime;

    private BigDecimal rating;

    @Field("total_reviews")
    private Integer totalReviews;

    private RestaurantStatus status;

    @Field("is_featured")
    private Boolean isFeatured;

    @Field("created_by")
    @CreatedBy
    private String createdBy;

    @Field("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Field("updated_by")
    @LastModifiedBy
    private String updatedBy;

    @Field("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
