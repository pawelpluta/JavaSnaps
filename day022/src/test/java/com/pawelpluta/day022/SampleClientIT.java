package com.pawelpluta.day022;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
class SampleClientIT {

    @Autowired
    SampleClient sampleClient;

    @Test
    void shouldRetryFailingRequest() {
        // when
        SampleClient.ResourceResponse response = sampleClient.call(111).get();

        // then
        assertEquals("Finished", getStateOfScenario("Fail once and then work"));
        assertEquals("field1 value for 111", response.field1());
        assertEquals("field2 value for 111", response.field2());
    }

    @Test
    void shouldRetryRequestOnlyOnce() {
        // when
        Optional<SampleClient.ResourceResponse> response = sampleClient.call(222);

        // then
        assertEquals("Failed for second time", getStateOfScenario("Fail twice and then work"));
        assertTrue(response.isEmpty());
    }

    private String getStateOfScenario(String scenarioName) {
        return WireMock.getAllScenarios().stream()
                .filter(scenario -> scenario.getName().equals(scenarioName))
                .map(Scenario::getState)
                .findFirst()
                .orElse("Scenario not found");
    }

}