package com.pawelpluta.day028;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
class SampleRestClientIT {

    private static final String VALID_RESPONSE_FROM_REMOTE_SERVICE = "Valid response from remote service";
    private static final String FALLBACK_RESPONSE = "Fallback response";

    @Autowired
    SampleRestClient restClient;

    @Autowired
    CircuitBreakerRegistry registry;

    @BeforeEach
    void closeCircuit() {
        for (CircuitBreaker circuitBreaker : registry.getAllCircuitBreakers()) {
            circuitBreaker.transitionToClosedState();
        }
    }

    @Test
    void shouldReturnResponseIfRemoteServiceWorks() {
        // given
        String idThatWorksOnRemoteService = "111";

        // when
        String response = restClient.getById(idThatWorksOnRemoteService);

        // then
        assertEquals(VALID_RESPONSE_FROM_REMOTE_SERVICE, response);
    }

    @Test
    void shouldReturnFallbackResponseIfRemoteServiceFails() {
        // given
        String idThatFailsOnRemoteService = "222";

        // when
        String response = restClient.getById(idThatFailsOnRemoteService);

        // then
        assertEquals(FALLBACK_RESPONSE, response);
    }

    @Test
    void shouldReturnFallbacksResponseWhenCircuitIsOpenEvenIfServiceWouldRespondOk() {
        // given
        String idThatTriggersScenario = "333";
        restClient.getById(idThatTriggersScenario); // first call to trigger scenario

        // when
        String response = restClient.getById(idThatTriggersScenario);

        // then
        assertEquals(FALLBACK_RESPONSE, response);
    }

    @Test
    void circuitShouldOpenAutomaticallyAfterWaitingInOpenState() {
        // given
        String idThatTriggersScenario = "444";
        restClient.getById(idThatTriggersScenario); // first call to trigger scenario

        // when
        waitTillOpenStateTimesOut();
        String response = restClient.getById(idThatTriggersScenario);

        // then
        assertEquals(FALLBACK_RESPONSE, response);
    }

    private void waitTillOpenStateTimesOut() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}