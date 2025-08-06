package com.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p.path("/quick-eats/orders/**")
                        .filters(f -> f.rewritePath("/quick-eats/orders/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("order-service-circuit-breaker")))
                        .uri("lb://ORDER-SERVICE"))
                .route(p -> p.path("/quick-eats/restaurants/**")
                        .filters(f -> f.rewritePath("/quick-eats/restaurants/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("restaurant-service-circuit-breaker")))
                        .uri("lb://RESTAURANT-SERVICE"))
                .route(p -> p.path("/quick-eats/users/**")
                        .filters(f -> f.rewritePath("/quick-eats/users/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("user-service-circuit-breaker")))
                        .uri("lb://USER-SERVICE"))
                .build();
    }

}
