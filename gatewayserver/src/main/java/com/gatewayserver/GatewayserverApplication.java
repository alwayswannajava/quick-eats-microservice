package com.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {
    private static final String TIME_RESPONSE_HEADER = "X-Response-Time";
    private static final String REWRITE_PATH_REPLACEMENT = "/${segment}";
    private static final String FALLBACK_URL = "forward:/contactSupport";

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p.path("/quick-eats/orders/**")
                        .filters(f -> f.rewritePath("/quick-eats/orders/(?<segment>.*)", REWRITE_PATH_REPLACEMENT)
                                .addResponseHeader(TIME_RESPONSE_HEADER, LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("order-service-circuit-breaker")
                                        .setFallbackUri(FALLBACK_URL)))
                        .uri("lb://ORDER-SERVICE"))
                .route(p -> p.path("/quick-eats/restaurants/**")
                        .filters(f -> f.rewritePath("/quick-eats/restaurants/(?<segment>.*)", REWRITE_PATH_REPLACEMENT)
                                .addResponseHeader(TIME_RESPONSE_HEADER, LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("restaurant-service-circuit-breaker")
                                        .setFallbackUri(FALLBACK_URL)))
                        .uri("lb://RESTAURANT-SERVICE"))
                .route(p -> p.path("/quick-eats/users/**")
                        .filters(f -> f.rewritePath("/quick-eats/users/(?<segment>.*)", REWRITE_PATH_REPLACEMENT)
                                .addResponseHeader(TIME_RESPONSE_HEADER, LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("user-service-circuit-breaker")
                                        .setFallbackUri(FALLBACK_URL)))
                        .uri("lb://USER-SERVICE"))
                .build();
    }

}
