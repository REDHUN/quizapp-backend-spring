package com.redhun.api_gateway.client;

import com.redhun.api_gateway.dto.TokenValidationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthServiceClient {

    private final WebClient webClient;

    public AuthServiceClient(WebClient.Builder webClientBuilder,
                             @Value("${auth-service.url}") String authServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }

    public Mono<TokenValidationResponse> validateToken(String token) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/auth/validate")
                        .queryParam("token", token)
                        .build())
                .retrieve()
                .bodyToMono(TokenValidationResponse.class);
    }
}