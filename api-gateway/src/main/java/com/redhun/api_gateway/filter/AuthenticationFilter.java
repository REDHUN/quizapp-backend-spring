package com.redhun.api_gateway.filter;

import com.redhun.api_gateway.client.AuthServiceClient;
import com.redhun.api_gateway.dto.TokenValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final AuthServiceClient authServiceClient;

    @Autowired
    public AuthenticationFilter(@Lazy AuthServiceClient authServiceClient) {
        super(Config.class);
        this.authServiceClient = authServiceClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Invalid authorization header", HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);

            return authServiceClient.validateToken(token)
                    .flatMap(response -> {
                        if (response.isValid()) {
                            // 🔹 Enforce Admin Access for `/api/admin/**`
                            String path = exchange.getRequest().getURI().getPath();
                            System.out.println("The path is "+path + response.getRoles());
                            if (path.startsWith("/api/admin/") && response.getRoles().stream().noneMatch(role -> role.equals("ROLE_ADMIN"))) {
                                return onError(exchange, "Access denied: Admins only", HttpStatus.FORBIDDEN);
                            }

                            // Add username & roles to request headers
                            ServerWebExchange modifiedExchange = exchange.mutate()
                                    .request(exchange.getRequest().mutate()
                                            .header("X-User-Username", response.getUsername())
                                            .header("X-User-Roles", String.join(",", response.getRoles()))
                                            .build())
                                    .build();
                            return chain.filter(modifiedExchange);
                        } else {
                            return onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED);
                        }
                    })
                    .onErrorResume(e -> {
                        e.printStackTrace();
                        if (e instanceof WebClientRequestException) {
                            return onError(exchange, "Auth service unavailable", HttpStatus.INTERNAL_SERVER_ERROR);
                        } else {
                            return onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED);
                        }
                    });
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // empty class as configuration properties are not required
    }
}
