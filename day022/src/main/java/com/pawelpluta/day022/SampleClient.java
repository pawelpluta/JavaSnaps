package com.pawelpluta.day022;

import feign.RetryableException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class SampleClient {

    private final FeignDummyClient client;

    SampleClient(FeignDummyClient client) {
        this.client = client;
    }

    Optional<ResourceResponse> call(Integer resourceId) {
        try {
            return Optional.ofNullable(client.getById(resourceId));
        } catch (RetryableException e) {
            return Optional.empty();
        }
    }

    record ResourceResponse(String field1, String field2) {
    }
}
