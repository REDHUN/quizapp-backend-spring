package com.redhun.api_gateway.config;

import com.redhun.api_gateway.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthenticationFilter authFilter;

    public GatewayConfig(AuthenticationFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Auth Service routes - no authentication needed
                .route("auth-service", r -> r.path("/api/auth/**").uri("lb://auth-service"))

                // Quiz Service routes - authentication required
                .route("quiz-service", r -> r.path("/api/quiz/**").filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config()))).uri("lb://quiz-service"))
                .route("question-service", r -> r.path("/api/questions/**").filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config()))).uri("lb://question-service"))
                .route("auth-service", r -> r.path("/api/user/**").filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config()))).uri("lb://auth-service"))
                .route("auth-service", r -> r.path("/api/admin/**").filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config()))).uri("lb://auth-service"))
                // Result Service routes - authentication required
                .route("result-service", r -> r
                        .path("/api/results/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://result-service"))
                .build();
    }
}