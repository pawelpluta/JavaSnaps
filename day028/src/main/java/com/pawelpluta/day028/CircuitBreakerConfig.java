package com.pawelpluta.day028;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
class CircuitBreakerConfig {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    CircuitBreakerConfig(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @PostConstruct
    void createCircuitBreakers() {
        io.github.resilience4j.circuitbreaker.CircuitBreakerConfig sampleRestClientCircuitBreakerConfig = io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.custom()
                .failureRateThreshold(20f)
                .minimumNumberOfCalls(1)
                .slidingWindowSize(5)
                .slidingWindowType(io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .maxWaitDurationInHalfOpenState(Duration.ofSeconds(5))
                .build();
        circuitBreakerRegistry.circuitBreaker("sample-rest-client", sampleRestClientCircuitBreakerConfig);
    }
}
