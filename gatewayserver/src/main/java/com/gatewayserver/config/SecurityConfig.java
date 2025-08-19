package com.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    private static final String ADMIN_SECURITY_ROLE = "ADMIN";
    private static final String USER_SECURITY_ROLE = "USER";
    private static final String RESTAURANT_OWNER_SECURITY_ROLE = "RESTAURANT_OWNER";

    private static final String USERS_GATEWAY_URL = "/quick-eats/users/**";
    private static final String RESTAURANTS_GATEWAY_URL = "/quick-eats/restaurants/**";
    private static final String ORDERS_GATEWAY_URL = "/quick-eats/orders/**";

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity.authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET, USERS_GATEWAY_URL)
                        .hasAnyRole(USER_SECURITY_ROLE, ADMIN_SECURITY_ROLE)
                        .pathMatchers(HttpMethod.POST, USERS_GATEWAY_URL).hasRole(ADMIN_SECURITY_ROLE)
                        .pathMatchers(HttpMethod.PATCH, USERS_GATEWAY_URL).hasRole(ADMIN_SECURITY_ROLE)
                        .pathMatchers(HttpMethod.DELETE, USERS_GATEWAY_URL).hasRole(ADMIN_SECURITY_ROLE)
                        .pathMatchers(HttpMethod.GET, RESTAURANTS_GATEWAY_URL).permitAll()
                        .pathMatchers(HttpMethod.POST, RESTAURANTS_GATEWAY_URL).hasRole(RESTAURANT_OWNER_SECURITY_ROLE)
                        .pathMatchers(HttpMethod.PATCH, RESTAURANTS_GATEWAY_URL).hasRole(RESTAURANT_OWNER_SECURITY_ROLE)
                        .pathMatchers(HttpMethod.DELETE, RESTAURANTS_GATEWAY_URL).hasRole(RESTAURANT_OWNER_SECURITY_ROLE)
                        .pathMatchers(HttpMethod.POST, ORDERS_GATEWAY_URL).hasRole(USER_SECURITY_ROLE)
                        .pathMatchers(HttpMethod.PATCH, ORDERS_GATEWAY_URL).hasRole(USER_SECURITY_ROLE)
                        .pathMatchers(HttpMethod.DELETE, ORDERS_GATEWAY_URL).hasRole(USER_SECURITY_ROLE))
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter =
                new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
                (new KeyCloakRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
