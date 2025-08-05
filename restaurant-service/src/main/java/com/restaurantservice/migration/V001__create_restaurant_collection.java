package com.restaurantservice.migration;

import com.restaurantservice.common.CuisineType;
import com.restaurantservice.common.RestaurantStatus;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.schema.JsonSchemaProperty;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.mongodb.core.validation.Validator;
import java.util.Arrays;

@ChangeUnit(id = "V001__create_restaurants_collection", order = "1", author = "Mykhailo Kornukh")
public class V001__create_restaurant_collection {

    @Execution
    public void createRestaurantsCollection(MongoTemplate mongoTemplate) {
        MongoJsonSchema schema = MongoJsonSchema.builder()
                .required(
                        "owner_id", "name", "cuisine_type", "phone", "email",
                        "address", "location", "working_hours", "delivery_fee", "min_order_amount",
                        "delivery_radius", "status", "created_by", "created_at"
                )
                .properties(
                        JsonSchemaProperty.string("owner_id").minLength(1),
                        JsonSchemaProperty.string("name").minLength(1).maxLength(255),
                        JsonSchemaProperty.string("description").maxLength(1000),
                        JsonSchemaProperty.array("cuisine_type").items(
                        ),
                        JsonSchemaProperty.string("logo"),
                        JsonSchemaProperty.string("cover_image"),
                        JsonSchemaProperty.string("phone").minLength(10).maxLength(20),
                        JsonSchemaProperty.string("email"),
                        JsonSchemaProperty.object("address")
                                .required("street", "city", "postal_code", "coordinates")
                                .properties(
                                        JsonSchemaProperty.string("street").minLength(1),
                                        JsonSchemaProperty.string("building").minLength(1),
                                        JsonSchemaProperty.string("city").minLength(1),
                                        JsonSchemaProperty.string("postal_code").minLength(1),
                                        JsonSchemaProperty.object("coordinates")
                                                .required("latitude", "longitude")
                                                .properties(
                                                        JsonSchemaProperty.decimal128("latitude"),
                                                        JsonSchemaProperty.decimal128("longitude")
                                                )
                                ),
                        // GeoJSON Point field for geospatial queries
                        JsonSchemaProperty.object("location")
                                .required("type", "coordinates")
                                .properties(
                                        JsonSchemaProperty.string("type").possibleValues("Point"),
                                        JsonSchemaProperty.array("coordinates")
                                                .minItems(2)
                                                .maxItems(2)
                                                .items(JsonSchemaProperty.decimal128("coordinate"))
                                ),
                        JsonSchemaProperty.object("working_hours")
                                .properties(
                                        JsonSchemaProperty.object("monday").properties(
                                                JsonSchemaProperty.string("open"),
                                                JsonSchemaProperty.string("close"),
                                                JsonSchemaProperty.bool("is_closed")
                                        ),
                                        JsonSchemaProperty.object("tuesday").properties(
                                                JsonSchemaProperty.string("open"),
                                                JsonSchemaProperty.string("close"),
                                                JsonSchemaProperty.bool("is_closed")
                                        ),
                                        JsonSchemaProperty.object("wednesday").properties(
                                                JsonSchemaProperty.string("open"),
                                                JsonSchemaProperty.string("close"),
                                                JsonSchemaProperty.bool("is_closed")
                                        ),
                                        JsonSchemaProperty.object("thursday").properties(
                                                JsonSchemaProperty.string("open"),
                                                JsonSchemaProperty.string("close"),
                                                JsonSchemaProperty.bool("is_closed")
                                        ),
                                        JsonSchemaProperty.object("friday").properties(
                                                JsonSchemaProperty.string("open"),
                                                JsonSchemaProperty.string("close"),
                                                JsonSchemaProperty.bool("is_closed")
                                        ),
                                        JsonSchemaProperty.object("saturday").properties(
                                                JsonSchemaProperty.string("open"),
                                                JsonSchemaProperty.string("close"),
                                                JsonSchemaProperty.bool("is_closed")
                                        ),
                                        JsonSchemaProperty.object("sunday").properties(
                                                JsonSchemaProperty.string("open"),
                                                JsonSchemaProperty.string("close"),
                                                JsonSchemaProperty.bool("is_closed")
                                        )
                                ),
                        JsonSchemaProperty.decimal128("delivery_fee"),
                        JsonSchemaProperty.decimal128("min_order_amount"),
                        JsonSchemaProperty.decimal128("delivery_radius"),
                        JsonSchemaProperty.int32("average_delivery_time"),
                        JsonSchemaProperty.decimal128("rating"),
                        JsonSchemaProperty.int32("total_reviews"),
                        JsonSchemaProperty.string("status").possibleValues(
                                RestaurantStatus.ACTIVE.name(),
                                RestaurantStatus.INACTIVE.name(),
                                RestaurantStatus.SUSPENDED.name()
                        ),
                        JsonSchemaProperty.bool("is_featured"),
                        JsonSchemaProperty.string("created_by"),
                        JsonSchemaProperty.date("created_at"),
                        JsonSchemaProperty.string("updated_by"),
                        JsonSchemaProperty.date("updated_at")
                )
                .build();

        mongoTemplate.createCollection("restaurants",
                CollectionOptions.empty()
                        .validator(Validator.schema(schema)));

        mongoTemplate.indexOps("restaurants").createIndex(
                new Index().on("email", Sort.Direction.ASC).unique().named("idx_restaurant_email")
        );
        mongoTemplate.indexOps("restaurants").createIndex(
                new Index().on("phone", Sort.Direction.ASC).unique().named("idx_restaurant_phone")
        );
        mongoTemplate.indexOps("restaurants").createIndex(
                new Index().on("owner_id", Sort.Direction.ASC).named("idx_restaurant_owner_id")
        );

        mongoTemplate.indexOps("restaurants").createIndex(
                new Index().on("address.coordinates", Sort.Direction.ASC).named("idx_restaurant_coordinates")
        );

        mongoTemplate.indexOps("restaurants").createIndex(
                new GeospatialIndex("location").typed(GeoSpatialIndexType.GEO_2DSPHERE)
                        .named("idx_restaurant_location_2dsphere")
        );

        mongoTemplate.indexOps("restaurants").createIndex(
                new Index()
                        .on("location", Sort.Direction.ASC)
                        .on("status", Sort.Direction.ASC)
                        .named("idx_restaurant_location_status")
        );

        mongoTemplate.indexOps("restaurants").createIndex(
                new Index()
                        .on("location", Sort.Direction.ASC)
                        .on("cuisine_type", Sort.Direction.ASC)
                        .named("idx_restaurant_location_cuisine")
        );
    }

    @RollbackExecution
    public void rollbackRestaurantsCollection(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection("restaurants");
    }
}
