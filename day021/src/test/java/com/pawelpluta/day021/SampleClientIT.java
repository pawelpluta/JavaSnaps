package com.pawelpluta.day021;

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
    void shouldReturnReceivedData() {
        // when
        SampleClient.ResourceResponse response = sampleClient.call(123).get();

        // then
        assertEquals("some value configured in mapping", response.field1());
        assertEquals("another value configured in mapping", response.field2());
    }

    @Test
    void shouldReturnEmptyOptionalWhenResourceIsNotFound() {
        // when
        Optional<SampleClient.ResourceResponse> response = sampleClient.call(999);

        // then
        assertTrue(response.isEmpty());
    }

}