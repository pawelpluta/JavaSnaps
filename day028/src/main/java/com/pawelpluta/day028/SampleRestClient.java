package com.pawelpluta.day028;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sampleClient", url = "${sampleClient.api.url}")
interface SampleRestClient {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "sample-rest-client", fallbackMethod = "fallbackGetById")
    String getById(@PathVariable("id") String id);

    default String fallbackGetById(String id, Exception exc) {
        return "Fallback response";
    }
}
