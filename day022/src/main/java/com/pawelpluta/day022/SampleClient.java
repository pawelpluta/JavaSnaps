package com.pawelpluta.day022;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
class SampleClient {

    private final String serviceAddress;
    private final String servicePath;
    private final RestTemplate restTemplate;

    SampleClient(
            @Value("${dummyClient.api.url}") String serviceAddress,
            @Value("${dummyClient.api.path}") String servicePath,
            RestTemplate restTemplate) {
        this.serviceAddress = serviceAddress;
        this.servicePath = servicePath;
        this.restTemplate = restTemplate;
    }

    Optional<ResourceResponse> call(Integer resourceId) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(
                    serviceAddress + servicePath,
                    ResourceResponse.class,
                    resourceId));
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() != HttpStatusCode.valueOf(404)) {
                throw e;
            }
            return Optional.empty();
        }
    }

    record ResourceResponse(String field1, String field2) {}
}
